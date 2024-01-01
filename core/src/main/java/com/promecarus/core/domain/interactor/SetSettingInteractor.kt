package com.promecarus.core.domain.interactor

import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.repository.SettingRepository
import com.promecarus.core.domain.usecase.SetSettingUseCase

class SetSettingInteractor(private val settingRepository: SettingRepository) : SetSettingUseCase {
    override suspend fun invoke(setting: Setting) = settingRepository.setSetting(setting)
}
