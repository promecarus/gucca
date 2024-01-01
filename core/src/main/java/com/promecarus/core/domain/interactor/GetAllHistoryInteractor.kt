package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.domain.repository.HistoryRepository
import com.promecarus.core.domain.usecase.GetAllHistoryUseCase
import kotlinx.coroutines.flow.Flow

class GetAllHistoryInteractor(private val historyRepository: HistoryRepository) :
    GetAllHistoryUseCase {
    override fun invoke(): Flow<List<HistoryEntity>> = historyRepository.getAll()
}
