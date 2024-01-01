package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.HistoryEntity

interface DeleteHistoryUseCase {
    suspend fun invoke(historyEntity: HistoryEntity)
}
