package com.aparokio.aparok.domain.vault

import com.aparokio.aparok.core_models.settings.VaultSettings
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class GetVaultSettings @Inject constructor(
    private val settings: UserSettingsRepository,
    private val auth: AuthRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<Unit, VaultSettings>(dispatchers.io) {

    override suspend fun doWork(params: Unit): VaultSettings {
        val acc = auth.getCurrentAccount()
        return settings.getVaultSettings(acc)
    }
}