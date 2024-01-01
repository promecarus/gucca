package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.repository.FavoriteRepository
import com.promecarus.core.domain.usecase.InsertFavoriteUseCase

class InsertFavoriteInteractor(private val favoriteRepository: FavoriteRepository) :
    InsertFavoriteUseCase {
    override suspend fun invoke(favoriteEntity: FavoriteEntity) =
        favoriteRepository.insert(favoriteEntity)
}
