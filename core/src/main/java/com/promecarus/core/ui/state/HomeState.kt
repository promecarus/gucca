package com.promecarus.core.ui.state

import com.promecarus.core.data.remote.ApiResponse
import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail

data class HomeState(
    var active: Boolean = false,
    var query: String = "",
    var openSettingDialog: Boolean = false,
    var setting: Setting = Setting(),
    var apiResponse: ApiResponse<Search> = ApiResponse.Loading,
    var showBottomSheet: Boolean = false,
    var userDetail: UserDetail = UserDetail(),
    var selectedTabIndex: Int = 0,
    var followers: List<User> = emptyList(),
    var following: List<User> = emptyList(),
)
