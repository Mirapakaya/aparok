package com.aparokio.aparok.middleware.interactor

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.aparokio.aparok.core_models.multiplayer.P2PStatus
import com.aparokio.aparok.core_models.multiplayer.P2PStatusUpdate
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncError
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncNetwork
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncStatus
import com.aparokio.aparok.core_models.multiplayer.SpaceSyncUpdate
import kotlin.test.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class SyncAndP2PStatusEventsStoreImplTest {

    private val dispatcher = StandardTestDispatcher(name = "Default test dispatcher")

    private val spaceId1 = "spaceId1"
    private val spaceId2 = "spaceId2"
    private val spaceId3 = "spaceId3"

    private lateinit var channel: EventHandlerChannelImpl

    @Before
    fun setUp() {
        channel = EventHandlerChannelImpl()
    }

    @Test
    fun `should update spaces p2p statuses`() = runTest(dispatcher) {

        turbineScope {

            val store = SyncAndP2PStatusEventsStoreImpl(
                channel = channel,
                dispatcher = dispatcher,
                scope = backgroundScope
            )

            val initialEvent = aparok.Event(
                messages = listOf(
                    aparok.Event.Message(
                        p2pStatusUpdate = aparok.Event.P2PStatus.Update(
                            spaceId = spaceId1,
                        )
                    ),
                    aparok.Event.Message(
                        p2pStatusUpdate = aparok.Event.P2PStatus.Update(
                            spaceId = spaceId2,
                            status = aparok.Event.P2PStatus.Status.Connected,
                            devicesCounter = 145
                        )
                    )
                )
            )

            val event1 = aparok.Event(
                messages = listOf(
                    aparok.Event.Message(
                        p2pStatusUpdate = aparok.Event.P2PStatus.Update(
                            spaceId = spaceId2,
                            status = aparok.Event.P2PStatus.Status.Connected,
                            devicesCounter = 233
                        )
                    )
                )
            )

            val event2 = aparok.Event(
                messages = listOf(
                    aparok.Event.Message(
                        p2pStatusUpdate = aparok.Event.P2PStatus.Update(
                            spaceId = spaceId1,
                            status = aparok.Event.P2PStatus.Status.NotPossible
                        )
                    )
                )
            )

            store.start()

            dispatcher.scheduler.advanceTimeBy(100)

            store.p2pStatus.test {
                val firstItem = awaitItem()
                assertEquals(mapOf(), firstItem)
                channel.dispatch(initialEvent)
                assertEquals(
                    expected = mapOf(
                        spaceId1 to P2PStatusUpdate.Update(
                            spaceId = spaceId1,
                            status = P2PStatus.NOT_CONNECTED,
                            devicesCounter = 0
                        )
                    ),
                    actual = awaitItem()
                )
                assertEquals(
                    expected = mapOf(
                        spaceId1 to P2PStatusUpdate.Update(
                            spaceId = spaceId1,
                            status = P2PStatus.NOT_CONNECTED,
                            devicesCounter = 0
                        ),
                        spaceId2 to P2PStatusUpdate.Update(
                            spaceId = spaceId2,
                            status = P2PStatus.CONNECTED,
                            devicesCounter = 145
                        )
                    ),
                    actual = awaitItem()
                )
                channel.dispatch(event1)
                assertEquals(
                    expected = mapOf(
                        spaceId1 to P2PStatusUpdate.Update(
                            spaceId = spaceId1,
                            status = P2PStatus.NOT_CONNECTED,
                            devicesCounter = 0
                        ),
                        spaceId2 to P2PStatusUpdate.Update(
                            spaceId = spaceId2,
                            status = P2PStatus.CONNECTED,
                            devicesCounter = 233
                        )
                    ),
                    actual = awaitItem()
                )
                channel.dispatch(event2)
                assertEquals(
                    expected = mapOf(
                        spaceId1 to P2PStatusUpdate.Update(
                            spaceId = spaceId1,
                            status = P2PStatus.NOT_POSSIBLE,
                            devicesCounter = 0
                        ),
                        spaceId2 to P2PStatusUpdate.Update(
                            spaceId = spaceId2,
                            status = P2PStatus.CONNECTED,
                            devicesCounter = 233
                        )
                    ),
                    actual = awaitItem()
                )
            }
        }
    }

    @Test
    fun `should update spaces sync statuses`() = runTest(dispatcher) {
        turbineScope {

            val store = SyncAndP2PStatusEventsStoreImpl(
                channel = channel,
                dispatcher = dispatcher,
                scope = backgroundScope
            )

            val initialEvent = aparok.Event(
                messages = listOf(
                    aparok.Event.Message(
                        spaceSyncStatusUpdate = aparok.Event.Space.SyncStatus.Update(
                            id = spaceId1,
                            status = aparok.Event.Space.Status.Offline,
                            network = aparok.Event.Space.Network.Aparok
                        )
                    ),
                    aparok.Event.Message(
                        spaceSyncStatusUpdate = aparok.Event.Space.SyncStatus.Update(
                            id = spaceId2,
                            status = aparok.Event.Space.Status.Syncing,
                            network = aparok.Event.Space.Network.SelfHost
                        )
                    )
                )
            )

            val event1 = aparok.Event(
                messages = listOf(
                    aparok.Event.Message(
                        spaceSyncStatusUpdate = aparok.Event.Space.SyncStatus.Update(
                            id = spaceId2,
                            status = aparok.Event.Space.Status.Error,
                            network = aparok.Event.Space.Network.SelfHost,
                            error = aparok.Event.Space.SyncError.StorageLimitExceed
                        )
                    ),
                    aparok.Event.Message(
                        spaceSyncStatusUpdate = aparok.Event.Space.SyncStatus.Update(
                            id = spaceId3,
                            status = aparok.Event.Space.Status.Synced,
                            network = aparok.Event.Space.Network.Aparok,
                            syncingObjectsCounter = 2345
                        )
                    )
                )
            )

            store.start()

            dispatcher.scheduler.advanceTimeBy(100)

            store.syncStatus.test {
                val firstItem = awaitItem()
                assertEquals(mapOf(), firstItem)

                channel.dispatch(initialEvent)

                assertEquals(
                    expected = mapOf(
                        spaceId1 to SpaceSyncUpdate.Update(
                            id = spaceId1,
                            status = SpaceSyncStatus.OFFLINE,
                            network = SpaceSyncNetwork.APAROK,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.NULL
                        )
                    ),
                    actual = awaitItem()
                )

                assertEquals(
                    expected = mapOf(
                        spaceId1 to SpaceSyncUpdate.Update(
                            id = spaceId1,
                            status = SpaceSyncStatus.OFFLINE,
                            network = SpaceSyncNetwork.APAROK,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.NULL
                        ),
                        spaceId2 to SpaceSyncUpdate.Update(
                            id = spaceId2,
                            status = SpaceSyncStatus.SYNCING,
                            network = SpaceSyncNetwork.SELF_HOST,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.NULL
                        )
                    ),
                    actual = awaitItem()
                )

                channel.dispatch(event1)

                assertEquals(
                    expected = mapOf(
                        spaceId1 to SpaceSyncUpdate.Update(
                            id = spaceId1,
                            status = SpaceSyncStatus.OFFLINE,
                            network = SpaceSyncNetwork.APAROK,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.NULL
                        ),
                        spaceId2 to SpaceSyncUpdate.Update(
                            id = spaceId2,
                            status = SpaceSyncStatus.ERROR,
                            network = SpaceSyncNetwork.SELF_HOST,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.STORAGE_LIMIT_EXCEED
                        )
                    ),
                    actual = awaitItem()
                )

                assertEquals(
                    expected = mapOf(
                        spaceId1 to SpaceSyncUpdate.Update(
                            id = spaceId1,
                            status = SpaceSyncStatus.OFFLINE,
                            network = SpaceSyncNetwork.APAROK,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.NULL
                        ),
                        spaceId2 to SpaceSyncUpdate.Update(
                            id = spaceId2,
                            status = SpaceSyncStatus.ERROR,
                            network = SpaceSyncNetwork.SELF_HOST,
                            syncingObjectsCounter = 0,
                            error = SpaceSyncError.STORAGE_LIMIT_EXCEED
                        ),
                        spaceId3 to SpaceSyncUpdate.Update(
                            id = spaceId3,
                            status = SpaceSyncStatus.SYNCED,
                            network = SpaceSyncNetwork.APAROK,
                            syncingObjectsCounter = 2345,
                            error = SpaceSyncError.NULL
                        )
                    ),
                    actual = awaitItem()
                )
            }
        }
    }
}