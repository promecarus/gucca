package com.promecarus.core.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.promecarus.core.ui.screen.FavoriteScreen
import com.promecarus.core.ui.state.FavoriteState
import com.promecarus.core.ui.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteRoute(viewModel: FavoriteViewModel = koinViewModel()) {
    @Composable
    fun getState() = viewModel.state.collectAsState(FavoriteState()).value

    FavoriteScreen(
        favorites = getState().favorites,
        showBottomSheet = getState().showBottomSheet,
        onShowBottomSheetChange = viewModel::onShowBottomSheetChange,
        userDetail = getState().userDetail,
        getUserDetail = viewModel::getUserDetail,
        selectedTabIndex = getState().selectedTabIndex,
        onSelectedTabIndexChange = viewModel::onSelectedTabIndexChange,
        followers = getState().followers,
        getFollowers = viewModel::getFollowers,
        following = getState().following,
        getFollowing = viewModel::getFollowing,
        favoriteIds = viewModel.getAllFavoriteId().collectAsState(initial = emptyList()).value,
        insert = viewModel::insert,
        delete = viewModel::delete,
    )
}
