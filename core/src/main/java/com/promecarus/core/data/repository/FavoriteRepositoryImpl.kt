package com.promecarus.core.data.repository

import com.promecarus.core.data.database.GuccaDatabase
import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(guccaDatabase: GuccaDatabase) : FavoriteRepository {
    private val favoriteDao = guccaDatabase.favoriteDao()

    override suspend fun insert(favoriteEntity: FavoriteEntity) = favoriteDao.insert(favoriteEntity)

    override fun getAll(): Flow<List<FavoriteEntity>> = favoriteDao.getAll()

    override fun getAllId(): Flow<List<Int>> = favoriteDao.getAllId()

    override suspend fun delete(favoriteEntity: FavoriteEntity) = favoriteDao.delete(favoriteEntity)
}
