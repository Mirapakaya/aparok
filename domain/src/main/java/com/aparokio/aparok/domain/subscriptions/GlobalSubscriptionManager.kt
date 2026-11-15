package com.aparokio.aparok.domain.subscriptions

import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.DeviceTokenStoringService
import com.aparokio.aparok.domain.device.NetworkConnectionStatus
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.notifications.PushKeyProvider
import com.aparokio.aparok.domain.search.HasInstanceOfObjectTypeSubscriptionManager
import com.aparokio.aparok.domain.search.ObjectTypesSubscriptionManager
import com.aparokio.aparok.domain.search.ProfileSubscriptionManager
import com.aparokio.aparok.domain.search.RelationOptionsSubscriptionManager
import com.aparokio.aparok.domain.search.RelationsSubscriptionManager
import com.aparokio.aparok.domain.spaces.SpaceDeletedStatusWatcher
import javax.inject.Inject

interface GlobalSubscriptionManager {

    fun onStart()
    fun onStop()

    class Default @Inject constructor(
        private val types: ObjectTypesSubscriptionManager,
        private val relations: RelationsSubscriptionManager,
        private val relationOptions: RelationOptionsSubscriptionManager,
        private val permissions: UserPermissionProvider,
        private val isSpaceDeleted: SpaceDeletedStatusWatcher,
        private val profile: ProfileSubscriptionManager,
        private val networkConnectionStatus: NetworkConnectionStatus,
        private val deviceTokenStoringService: DeviceTokenStoringService,
        private val pushKeyProvider: PushKeyProvider,
        private val hasInstanceOfType: HasInstanceOfObjectTypeSubscriptionManager,
        private val logger: Logger
    ) : GlobalSubscriptionManager {

        override fun onStart() {
            logger.logInfo("GlobalSubscriptionManager: Starting all subscriptions")
            pushKeyProvider.start()
            types.onStart()
            relations.onStart()
            relationOptions.onStart()
            permissions.start()
            isSpaceDeleted.onStart()
            profile.onStart()
            networkConnectionStatus.start()
            deviceTokenStoringService.start()
            hasInstanceOfType.onStart()
        }

        override fun onStop() {
            logger.logInfo("GlobalSubscriptionManager: Stopping all subscriptions")
            pushKeyProvider.stop()
            types.onStop()
            relations.onStop()
            relationOptions.onStop()
            permissions.stop()
            isSpaceDeleted.onStop()
            profile.onStop()
            networkConnectionStatus.stop()
            deviceTokenStoringService.stop()
            hasInstanceOfType.onStop()
        }
    }

}