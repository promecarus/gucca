package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.FavoriteEntity

interface InsertFavoriteUseCase {
    suspend fun invoke(favoriteEntity: FavoriteEntity)
}
