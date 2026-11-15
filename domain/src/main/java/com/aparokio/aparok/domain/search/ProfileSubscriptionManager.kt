package com.aparokio.aparok.domain.search

import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.library.StoreSearchByIdsParams
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.subscriptions.GlobalSubscription
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

interface ProfileSubscriptionManager : GlobalSubscription {

    fun observe(): Flow<ObjectWrapper.Basic>
    fun onStart()
    fun onStop()

    class Default @Inject constructor(
        private val scope: CoroutineScope,
        private val configStorage: ConfigStorage,
        private val awaitAccountStartManager: AwaitAccountStartManager,
        private val container: StorelessSubscriptionContainer,
        private val dispatchers: AppCoroutineDispatchers,
        private val logger: Logger
    ) : ProfileSubscriptionManager {

        private val state = MutableStateFlow<ObjectWrapper.Basic?>(null)

        override fun observe(): Flow<ObjectWrapper.Basic> {
            return state.mapNotNull { it }
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        override fun onStart() {
            scope.launch {
                awaitAccountStartManager
                    .state()
                    .mapNotNull { configStorage.getOrNull() }
                    .flatMapLatest { config ->
                        container.subscribe(
                            searchParams = StoreSearchByIdsParams(
                                subscription = GLOBAL_PROFILE_SUBSCRIPTION,
                                space = SpaceId(config.techSpace),
                                keys = listOf(
                                    Relations.ID,
                                    Relations.NAME,
                                    Relations.ICON_EMOJI,
                                    Relations.ICON_NAME,
                                    Relations.ICON_IMAGE,
                                    Relations.ICON_OPTION,
                                    Relations.SHARED_SPACES_LIMIT
                                ),
                                targets = listOf(config.profile)
                            )
                        ).map {
                            it.firstOrNull()
                        }
                    }
                    .flowOn(dispatchers.io)
                    .catch { logger.logException(it, "Error in ProfileSubscriptionManager") }
                    .collect {
                        state.value = it
                    }
            }
        }

        override fun onStop() {
            scope.launch(dispatchers.io) {
                container.unsubscribe(listOf(GLOBAL_PROFILE_SUBSCRIPTION))
            }
        }

        companion object {
            const val GLOBAL_PROFILE_SUBSCRIPTION = "subscription.global.profile"
        }
    }
}