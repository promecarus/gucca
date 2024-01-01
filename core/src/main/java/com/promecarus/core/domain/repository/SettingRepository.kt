package com.promecarus.core.domain.repository

import com.promecarus.core.domain.model.Setting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getSetting(): Flow<Setting>

    suspend fun setSetting(setting: Setting)
}
