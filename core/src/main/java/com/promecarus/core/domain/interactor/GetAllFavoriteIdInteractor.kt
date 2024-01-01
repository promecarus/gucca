package com.promecarus.core.domain.interactor

import com.promecarus.core.domain.repository.FavoriteRepository
import com.promecarus.core.domain.usecase.GetAllFavoriteIdUseCase
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteIdInteractor(private val favoriteRepository: FavoriteRepository) :
    GetAllFavoriteIdUseCase {
    override fun invoke(): Flow<List<Int>> = favoriteRepository.getAllId()
}
