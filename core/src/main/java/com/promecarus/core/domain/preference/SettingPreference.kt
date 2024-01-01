package com.promecarus.core.domain.preference

import com.promecarus.core.domain.model.Setting
import kotlinx.coroutines.flow.Flow

interface SettingPreference {
    fun getSetting(): Flow<Setting>

    suspend fun setSetting(setting: Setting)
}
