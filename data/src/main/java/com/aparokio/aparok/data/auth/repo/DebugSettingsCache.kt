package com.aparokio.aparok.data.auth.repo

interface DebugSettingsCache {
    suspend fun enableAparokContextMenu()
    suspend fun disableAparokContextMenu()
    suspend fun getAparokContextMenu(): Boolean
}