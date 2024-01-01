package com.promecarus.core.data.repository

import com.promecarus.core.data.database.GuccaDatabase
import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(guccaDatabase: GuccaDatabase) : HistoryRepository {
    private val historyDao = guccaDatabase.historyDao()

    override suspend fun insert(historyEntity: HistoryEntity) = historyDao.insert(historyEntity)

    override fun getAll(): Flow<List<HistoryEntity>> = historyDao.getAll()

    override suspend fun delete(historyEntity: HistoryEntity) = historyDao.delete(historyEntity)
}
