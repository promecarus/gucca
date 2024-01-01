package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.repository.FavoriteRepository
import com.promecarus.core.domain.usecase.DeleteFavoriteUseCase

class DeleteFavoriteInteractor(private val favoriteRepository: FavoriteRepository) :
    DeleteFavoriteUseCase {
    override suspend fun invoke(favoriteEntity: FavoriteEntity) =
        favoriteRepository.delete(favoriteEntity)
}
