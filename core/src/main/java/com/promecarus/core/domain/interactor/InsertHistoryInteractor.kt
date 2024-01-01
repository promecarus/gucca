package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.domain.repository.HistoryRepository
import com.promecarus.core.domain.usecase.InsertHistoryUseCase

class InsertHistoryInteractor(private val historyRepository: HistoryRepository) :
    InsertHistoryUseCase {
    override suspend fun invoke(historyEntity: HistoryEntity) =
        historyRepository.insert(historyEntity)
}
