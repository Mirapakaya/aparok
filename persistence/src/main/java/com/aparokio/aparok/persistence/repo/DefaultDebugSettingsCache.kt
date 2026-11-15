package com.aparokio.aparok.persistence.repo

import android.content.SharedPreferences
import com.aparokio.aparok.data.auth.repo.DebugSettingsCache

class DefaultDebugSettingsCache(private val prefs: SharedPreferences) : DebugSettingsCache {

    override suspend fun enableAparokContextMenu() {
        prefs.edit().putBoolean(ACTION_MODE, true).apply()
    }

    override suspend fun disableAparokContextMenu() {
        prefs.edit().putBoolean(ACTION_MODE, false).apply()
    }

    override suspend fun getAparokContextMenu(): Boolean = prefs.getBoolean(ACTION_MODE, true)

    companion object {
        val ACTION_MODE = "prefs.action_mode"
    }
}