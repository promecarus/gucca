package com.promecarus.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    val login: String,
    @PrimaryKey val id: Int,
    val avatarUrl: String,
    val type: String,
)
