package com.promecarus.core.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.preference.SettingPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferenceImpl(private val dataStore: DataStore<Preferences>) : SettingPreference {
    override fun getSetting(): Flow<Setting> = dataStore.data.map {
        Setting(
            search = it[SEARCH] ?: 20,
            followers = it[FOLLOWERS] ?: 10,
            following = it[FOLLOWING] ?: 10,
        )
    }

    override suspend fun setSetting(setting: Setting) {
        dataStore.edit {
            it[SEARCH] = setting.search
            it[FOLLOWERS] = setting.followers
            it[FOLLOWING] = setting.following
        }
    }

    companion object {
        private val SEARCH = intPreferencesKey("search")
        private val FOLLOWERS = intPreferencesKey("followers")
        private val FOLLOWING = intPreferencesKey("following")
    }
}
