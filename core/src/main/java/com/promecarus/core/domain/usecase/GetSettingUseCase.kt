package com.promecarus.core.domain.usecase

import com.promecarus.core.domain.model.Setting
import kotlinx.coroutines.flow.Flow

interface GetSettingUseCase {
    fun invoke(): Flow<Setting>
}
