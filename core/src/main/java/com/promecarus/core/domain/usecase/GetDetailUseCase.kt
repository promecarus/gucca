package com.promecarus.core.domain.usecase

import com.promecarus.core.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetDetailUseCase {
    suspend fun invoke(login: String): Flow<UserDetail>
}
