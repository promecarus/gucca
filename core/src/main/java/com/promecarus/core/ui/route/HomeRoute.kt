package com.promecarus.core.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.promecarus.core.ui.screen.HomeScreen
import com.promecarus.core.ui.state.HomeState
import com.promecarus.core.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(viewModel: HomeViewModel = koinViewModel()) {
    @Composable
    fun getState() = viewModel.state.collectAsState(HomeState()).value

    HomeScreen(
        active = getState().active,
        onActiveChange = viewModel::onActiveChange,
        query = getState().query,
        onQueryChange = viewModel::onQueryChange,
        onSearch = viewModel::search,
        openSettingDialog = getState().openSettingDialog,
        onOpenSettingDialogChange = viewModel::onOpenSettingDialogChange,
        setting = getState().setting,
        onSettingChange = viewModel::onSettingChange,
        onSettingTempChange = viewModel::onSettingTempChange,
        insertHistory = viewModel::insertHistory,
        histories = viewModel.getAllHistory().collectAsState(initial = emptyList()).value,
        deleteHistory = viewModel::deleteHistory,
        apiResponse = getState().apiResponse,
        clearQuery = viewModel::onQueryChange,
        showBottomSheet = getState().showBottomSheet,
        onShowBottomSheetChange = viewModel::showBottomSheetChange,
        userDetail = getState().userDetail,
        getUserDetail = viewModel::getUserDetail,
        selectedTabIndex = getState().selectedTabIndex,
        onSelectedTabIndexChange = viewModel::onSelectedTabIndexChange,
        followers = getState().followers,
        getFollowers = viewModel::getFollowers,
        following = getState().following,
        getFollowing = viewModel::getFollowing,
        favoriteIds = viewModel.getAllFavoriteId().collectAsState(initial = emptyList()).value,
        insertFavorite = viewModel::insertFavorite,
        deleteFavorite = viewModel::deleteFavorite,
    )
}
