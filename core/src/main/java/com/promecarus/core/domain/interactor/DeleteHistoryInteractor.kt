package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.domain.repository.HistoryRepository
import com.promecarus.core.domain.usecase.DeleteHistoryUseCase

class DeleteHistoryInteractor(private val historyRepository: HistoryRepository) :
    DeleteHistoryUseCase {
    override suspend fun invoke(historyEntity: HistoryEntity) =
        historyRepository.delete(historyEntity)
}
