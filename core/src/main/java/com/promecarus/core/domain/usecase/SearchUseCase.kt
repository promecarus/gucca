package com.promecarus.core.domain.usecase

import com.promecarus.core.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun invoke(query: String): Flow<Search>
}
