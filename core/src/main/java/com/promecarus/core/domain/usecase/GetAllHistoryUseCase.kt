package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface GetAllHistoryUseCase {
    fun invoke(): Flow<List<HistoryEntity>>
}
