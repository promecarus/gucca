package com.promecarus.core.ui.state

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail

data class FavoriteState(
    var favorites: List<FavoriteEntity> = emptyList(),
    var showBottomSheet: Boolean = false,
    var userDetail: UserDetail = UserDetail(),
    var selectedTabIndex: Int = 0,
    var followers: List<User> = emptyList(),
    var following: List<User> = emptyList(),
)
