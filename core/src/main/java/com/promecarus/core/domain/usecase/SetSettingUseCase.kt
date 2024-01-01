package com.promecarus.core.domain.usecase

import com.promecarus.core.domain.model.Setting

interface SetSettingUseCase {
    suspend fun invoke(setting: Setting)
}
