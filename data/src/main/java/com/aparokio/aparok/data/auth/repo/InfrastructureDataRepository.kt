package com.aparokio.aparok.data.auth.repo

import com.aparokio.aparok.domain.config.InfrastructureRepository

class InfrastructureDataRepository(
    private val cache: DebugSettingsCache
) : InfrastructureRepository {

    override suspend fun enableAparokContextMenu() {
        cache.enableAparokContextMenu()
    }

    override suspend fun disableAparokContextMenu() {
        cache.disableAparokContextMenu()
    }

    override suspend fun getAparokContextMenu(): Boolean = cache.getAparokContextMenu()
}