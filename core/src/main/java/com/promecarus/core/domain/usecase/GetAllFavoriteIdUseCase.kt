package com.promecarus.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetAllFavoriteIdUseCase {
    fun invoke(): Flow<List<Int>>
}
