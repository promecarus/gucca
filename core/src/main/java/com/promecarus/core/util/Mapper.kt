package com.promecarus.core.util

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.model.User

object Mapper {
    fun FavoriteEntity.toUser() = User(
        login = login,
        id = id,
        avatar_url = avatarUrl,
        type = type,
    )

    fun User.toFavoriteEntity() = FavoriteEntity(
        login = login,
        id = id,
        avatarUrl = avatar_url,
        type = type,
    )
}
