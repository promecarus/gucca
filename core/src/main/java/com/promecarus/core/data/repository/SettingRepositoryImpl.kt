package com.promecarus.core.data.repository

import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.preference.SettingPreference
import com.promecarus.core.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepositoryImpl(private val settingPreference: SettingPreference) : SettingRepository {
    override fun getSetting(): Flow<Setting> = settingPreference.getSetting()

    override suspend fun setSetting(setting: Setting) = settingPreference.setSetting(setting)
}
