package com.promecarus.core.domain.interactor

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.repository.FavoriteRepository
import com.promecarus.core.domain.usecase.GetAllFavoriteUseCase
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteInteractor(private val favoriteRepository: FavoriteRepository) :
    GetAllFavoriteUseCase {
    override fun invoke(): Flow<List<FavoriteEntity>> = favoriteRepository.getAll()
}
