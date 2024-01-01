package com.promecarus.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.data.remote.ApiResponse
import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.usecase.DeleteFavoriteUseCase
import com.promecarus.core.domain.usecase.DeleteHistoryUseCase
import com.promecarus.core.domain.usecase.GetAllFavoriteIdUseCase
import com.promecarus.core.domain.usecase.GetAllHistoryUseCase
import com.promecarus.core.domain.usecase.GetDetailUseCase
import com.promecarus.core.domain.usecase.GetFollowersUseCase
import com.promecarus.core.domain.usecase.GetFollowingUseCase
import com.promecarus.core.domain.usecase.GetSettingUseCase
import com.promecarus.core.domain.usecase.InsertFavoriteUseCase
import com.promecarus.core.domain.usecase.InsertHistoryUseCase
import com.promecarus.core.domain.usecase.SearchUseCase
import com.promecarus.core.domain.usecase.SetSettingUseCase
import com.promecarus.core.ui.state.HomeState
import com.promecarus.core.util.Mapper.toFavoriteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase,
    private val getAllFavoriteIdUseCase: GetAllFavoriteIdUseCase,
    private val getAllHistoryUseCase: GetAllHistoryUseCase,
    private val getDetailUseCase: GetDetailUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val getSettingUseCase: GetSettingUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val insertHistoryUseCase: InsertHistoryUseCase,
    private val searchUseCase: SearchUseCase,
    private val setSettingUseCase: SetSettingUseCase,
) : ViewModel() {
    var state = MutableStateFlow(HomeState())
        private set

    init {
        state.update { homeState ->
            homeState.copy(
                query = runBlocking {
                    getAllHistoryUseCase.invoke().firstOrNull()?.firstOrNull()?.query ?: ""
                },
                setting = runBlocking {
                    getSettingUseCase.invoke().firstOrNull()
                        ?: Setting()
                },
            )
        }

        search(state.value.query)
    }

    fun onActiveChange(active: Boolean = false) = state.update { homeState ->
        homeState.copy(active = active)
    }

    fun onQueryChange(query: String = "") = state.update { homeState ->
        homeState.copy(query = query)
    }

    fun search(query: String) = viewModelScope.launch {
        searchUseCase.invoke(query).onStart {
            state.update { homeState ->
                homeState.copy(apiResponse = ApiResponse.Loading)
            }
        }.catch {
            state.update { homeState ->
                homeState.copy(
                    apiResponse = ApiResponse.Error(it.message?.errorHandler() ?: "Unknown error")
                )
            }
        }.collect {
            state.update { homeState ->
                homeState.copy(apiResponse = ApiResponse.Success(it))
            }
        }
    }

    fun onOpenSettingDialogChange(openSettingDialog: Boolean) = state.update { homeState ->
        homeState.copy(openSettingDialog = openSettingDialog)
    }

    fun onSettingChange(setting: Setting) = viewModelScope.launch {
        setSettingUseCase.invoke(setting)
    }

    fun onSettingTempChange(setting: Setting) =
        state.update { homeState ->
            homeState.copy(setting = setting)
        }

    fun insertHistory(query: String) = viewModelScope.launch {
        insertHistoryUseCase.invoke(
            HistoryEntity(
                query = query,
                timestamp = System.currentTimeMillis(),
            )
        )
    }

    fun getAllHistory() = getAllHistoryUseCase.invoke()

    fun deleteHistory(historyEntity: HistoryEntity) = viewModelScope.launch {
        deleteHistoryUseCase.invoke(historyEntity)
    }

    fun showBottomSheetChange(showBottomSheet: Boolean) = state.update { homeState ->
        homeState.copy(showBottomSheet = showBottomSheet)
    }

    fun getUserDetail(login: String) = viewModelScope.launch {
        getDetailUseCase.invoke(login).catch {}.collect {
            state.update { homeState ->
                homeState.copy(userDetail = it)
            }
        }
    }

    fun onSelectedTabIndexChange(selectedTabIndex: Int) = state.update { homeState ->
        homeState.copy(selectedTabIndex = selectedTabIndex)
    }

    fun getFollowers(username: String) = viewModelScope.launch {
        getFollowersUseCase.invoke(username).catch {}.collect {
            state.update { homeState ->
                homeState.copy(followers = it)
            }
        }
    }

    fun getFollowing(username: String) = viewModelScope.launch {
        getFollowingUseCase.invoke(username).catch {}.collect {
            state.update { homeState ->
                homeState.copy(following = it)
            }
        }
    }

    fun getAllFavoriteId() = getAllFavoriteIdUseCase.invoke()

    fun insertFavorite(user: User) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(user.toFavoriteEntity())
    }

    fun deleteFavorite(user: User) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(user.toFavoriteEntity())
    }

    private fun String.errorHandler(): String = when {
        this.contains("No address") -> "No internet connection"
        this.contains("403") -> "API rate limit exceeded"
        this.contains("422") -> "Empty query"
        else -> this
    }
}
