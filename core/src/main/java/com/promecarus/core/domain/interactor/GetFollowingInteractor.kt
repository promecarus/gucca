package com.promecarus.core.domain.interactor

import com.promecarus.core.BuildConfig
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.repository.SettingRepository
import com.promecarus.core.domain.repository.UserRepository
import com.promecarus.core.domain.usecase.GetFollowingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetFollowingInteractor(
    private val settingRepository: SettingRepository,
    private val userRepository: UserRepository,
) : GetFollowingUseCase {
    override suspend fun invoke(username: String): Flow<List<User>> = userRepository.getFollowing(
        token = "token ${BuildConfig.API_KEY}",
        username = username,
        perPage = settingRepository.getSetting().first().following,
    )
}
