package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.HistoryEntity

interface InsertHistoryUseCase {
    suspend fun invoke(historyEntity: HistoryEntity)
}
