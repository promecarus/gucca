package com.promecarus.core.domain.repository

import com.promecarus.core.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insert(historyEntity: HistoryEntity)

    fun getAll(): Flow<List<HistoryEntity>>

    suspend fun delete(historyEntity: HistoryEntity)
}
