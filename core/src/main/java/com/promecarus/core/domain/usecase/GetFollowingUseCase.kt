package com.promecarus.core.domain.usecase

import com.promecarus.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetFollowingUseCase {
    suspend fun invoke(username: String): Flow<List<User>>
}
