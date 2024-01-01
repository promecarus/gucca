package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface GetAllFavoriteUseCase {
    fun invoke(): Flow<List<FavoriteEntity>>
}
