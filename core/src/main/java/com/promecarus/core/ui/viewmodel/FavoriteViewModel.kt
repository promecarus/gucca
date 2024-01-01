package com.promecarus.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.usecase.DeleteFavoriteUseCase
import com.promecarus.core.domain.usecase.GetAllFavoriteIdUseCase
import com.promecarus.core.domain.usecase.GetAllFavoriteUseCase
import com.promecarus.core.domain.usecase.GetDetailUseCase
import com.promecarus.core.domain.usecase.GetFollowersUseCase
import com.promecarus.core.domain.usecase.GetFollowingUseCase
import com.promecarus.core.domain.usecase.InsertFavoriteUseCase
import com.promecarus.core.ui.state.FavoriteState
import com.promecarus.core.util.Mapper.toFavoriteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getAllFavoriteIdUseCase: GetAllFavoriteIdUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
    private val getDetailUseCase: GetDetailUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
) : ViewModel() {
    var state = MutableStateFlow(FavoriteState())
        private set

    init {
        viewModelScope.launch {
            getAllFavoriteUseCase.invoke().collect {
                state.update { favoriteState ->
                    favoriteState.copy(favorites = it)
                }
            }
        }
    }

    fun onShowBottomSheetChange(showBottomSheet: Boolean) = state.update { favoriteState ->
        favoriteState.copy(showBottomSheet = showBottomSheet)
    }

    fun getUserDetail(userId: String) = viewModelScope.launch {
        getDetailUseCase.invoke(userId).catch {}.collect {
            state.update { favoriteState ->
                favoriteState.copy(userDetail = it)
            }
        }
    }

    fun onSelectedTabIndexChange(selectedTabIndex: Int) = state.update { favoriteState ->
        favoriteState.copy(selectedTabIndex = selectedTabIndex)
    }

    fun getFollowers(userId: String) = viewModelScope.launch {
        getFollowersUseCase.invoke(userId).catch {}.collect {
            state.update { favoriteState ->
                favoriteState.copy(followers = it)
            }
        }
    }

    fun getFollowing(userId: String) = viewModelScope.launch {
        getFollowingUseCase.invoke(userId).catch {}.collect {
            state.update { favoriteState ->
                favoriteState.copy(following = it)
            }
        }
    }

    fun getAllFavoriteId() = getAllFavoriteIdUseCase.invoke()

    fun insert(user: User) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(user.toFavoriteEntity())
    }

    fun delete(user: User) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(user.toFavoriteEntity())
    }
}
