package com.aparokio.aparok.domain.auth.interactor

import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.Interactor
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.workspace.SpaceManager
import javax.inject.Inject

/**
 * Use case for logging out.
 */
class Logout @Inject constructor(
    private val repo: AuthRepository,
    private val config: ConfigStorage,
    private val user: UserSettingsRepository,
    private val spaceManager: SpaceManager,
    private val awaitAccountStartManager: AwaitAccountStartManager,
    private val remainingSpacesPreloader: RemainingSpacesPreloader,
    private val preferredSpaceIdHolder: PreferredSpaceIdHolder,
    dispatchers: AppCoroutineDispatchers,
) : Interactor<Logout.Params>(context = dispatchers.io) {

    override suspend fun run(params: Params) {
        repo.logout(clearLocalRepositoryData = params.clearLocalRepositoryData)
        user.clear()
        config.clear()
        spaceManager.clear()
        remainingSpacesPreloader.reset()
        // Drop any preferred space id set but not consumed this process (e.g. a
        // cold-start deeplink that lost the LaunchAccount race), so it can't leak
        // into the next login's LaunchAccount.
        preferredSpaceIdHolder.clear()
        awaitAccountStartManager.setState(AwaitAccountStartManager.State.Stopped)
    }

    class Params(
        val clearLocalRepositoryData: Boolean = false
    )
}