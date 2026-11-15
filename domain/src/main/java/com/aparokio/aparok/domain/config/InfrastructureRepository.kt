package com.aparokio.aparok.domain.config

interface InfrastructureRepository {
    suspend fun enableAparokContextMenu()
    suspend fun disableAparokContextMenu()
    suspend fun getAparokContextMenu(): Boolean
}