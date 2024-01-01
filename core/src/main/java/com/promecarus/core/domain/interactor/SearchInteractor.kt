package com.promecarus.core.domain.interactor

import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.repository.SettingRepository
import com.promecarus.core.domain.repository.UserRepository
import com.promecarus.core.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class SearchInteractor(
    private val settingRepository: SettingRepository,
    private val userRepository: UserRepository,
) : SearchUseCase {
    override suspend fun invoke(query: String): Flow<Search> = userRepository.search(
        query = query,
        perPage = settingRepository.getSetting().first().search,
    )
}
