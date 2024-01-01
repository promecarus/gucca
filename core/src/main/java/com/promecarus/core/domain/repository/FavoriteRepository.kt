package com.promecarus.core.domain.repository

import com.promecarus.core.data.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insert(favoriteEntity: FavoriteEntity)

    fun getAll(): Flow<List<FavoriteEntity>>

    fun getAllId(): Flow<List<Int>>

    suspend fun delete(favoriteEntity: FavoriteEntity)
}
