package com.promecarus.core.domain.usecase

import com.promecarus.core.data.database.entity.FavoriteEntity

interface DeleteFavoriteUseCase {
    suspend fun invoke(favoriteEntity: FavoriteEntity)
}
