package com.promecarus.core.domain.interactor

import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.repository.SettingRepository
import com.promecarus.core.domain.usecase.GetSettingUseCase
import kotlinx.coroutines.flow.Flow

class GetSettingInteractor(private val settingRepository: SettingRepository) : GetSettingUseCase {
    override fun invoke(): Flow<Setting> = settingRepository.getSetting()
}
