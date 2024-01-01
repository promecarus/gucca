package com.promecarus.core.domain.interactor

import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.domain.repository.UserRepository
import com.promecarus.core.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.flow.Flow

class GetDetailInteractor(private val userRepository: UserRepository) : GetDetailUseCase {
    override suspend fun invoke(login: String): Flow<UserDetail> =
        userRepository.getDetail(login = login)
}
