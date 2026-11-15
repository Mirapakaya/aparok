package com.aparokio.aparok.domain.vault

import com.aparokio.aparok.core_models.settings.VaultSettings
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.FlowInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest

class ObserveVaultSettings @Inject constructor(
    private val awaitAccountStart: AwaitAccountStartManager,
    private val settings: UserSettingsRepository,
    private val auth: AuthRepository,
    private val logger: Logger,
    dispatchers: AppCoroutineDispatchers
): FlowInteractor<Unit, VaultSettings>(dispatchers.io) {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun build(): Flow<VaultSettings> = awaitAccountStart
        .awaitStart()
        .flatMapLatest {
            val acc = auth.getCurrentAccount()
            settings.observeVaultSettings(acc)
        }.catch {
            logger.logException(it, "Error while observing vault settings")
            emit(VaultSettings.default())
        }

    override fun build(params: Unit): Flow<VaultSettings> = build()
}