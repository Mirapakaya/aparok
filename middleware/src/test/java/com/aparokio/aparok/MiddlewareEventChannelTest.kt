package com.aparokio.aparok
import com.aparokio.aparok.middleware.EventGroup

import aparok.model.Block
import com.aparokio.aparok.core_models.Event
import com.aparokio.aparok.core_models.ThemeColor
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.MiddlewareEventChannel
import com.aparokio.aparok.test_utils.MockDataFactory
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import kotlin.test.assertEquals

class MiddlewareEventChannelTest {

    @Mock
    lateinit var proxy: EventProxy

    @Mock
    lateinit var featureToggles: FeatureToggles

    private lateinit var channel: MiddlewareEventChannel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        channel = MiddlewareEventChannel(
            events = proxy,
            featureToggles = featureToggles
        )
    }

    @Test
    fun `should filter event by context and pass it downstream`() {

        val context = MockDataFactory.randomUuid()
        val id = MockDataFactory.randomUuid()

        val msg = aparok.Event.Block.Set.BackgroundColor(
            id = id,
            backgroundColor = ThemeColor.YELLOW.code
        )

        val message = aparok.Event.Message(
            blockSetBackgroundColor = msg
        )

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.GranularChange(
                id = id,
                context = context,
                backgroundColor = ThemeColor.YELLOW.code
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should filter event by context and do not pass it downstream`() {

        val context = MockDataFactory.randomUuid()

        val msg = aparok.Event.Block.Set.BackgroundColor(
            backgroundColor = ThemeColor.YELLOW.code
        )

        val message = aparok.Event.Message(
            blockSetBackgroundColor = msg
        )

        val event =
            aparok.Event(contextId = MockDataFactory.randomUuid(), messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = emptyList(),
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should pass event downstream if context for filtering is not provided`() {

        val context = MockDataFactory.randomUuid()
        val id = MockDataFactory.randomUuid()

        val msg = aparok.Event.Block.Set.BackgroundColor(
            id = id,
            backgroundColor = ThemeColor.YELLOW.code
        )

        val message = aparok.Event.Message(
            blockSetBackgroundColor = msg
        )

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.GranularChange(
                id = id,
                context = context,
                backgroundColor = ThemeColor.YELLOW.code
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should return UpdateBlockFile event`() {

        val targetObjectId = "785687346534hfjdbsjfbds"
        val name = "video1.mp4"
        val mime = "video/*"
        val size = 999111L
        val state = Block.Content.File.State.Done
        val type = Block.Content.File.Type.Video

        val context = MockDataFactory.randomUuid()
        val id = MockDataFactory.randomUuid()

        val msg = aparok.Event.Block.Set.File(
            id = id,
            targetObjectId = aparok.Event.Block.Set.File.TargetObjectId(targetObjectId),
            mime = aparok.Event.Block.Set.File.Mime(mime),
            size = aparok.Event.Block.Set.File.Size(size),
            type = aparok.Event.Block.Set.File.Type(type),
            state = aparok.Event.Block.Set.File.State(state),
            name = aparok.Event.Block.Set.File.Name(name)
        )

        val message = aparok.Event.Message(blockSetFile = msg)

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.UpdateFileBlock(
                context = context,
                blockId = id,
                targetObjectId = targetObjectId,
                mime = mime,
                size = size,
                type = com.aparokio.aparok.core_models.Block.Content.File.Type.VIDEO,
                state = com.aparokio.aparok.core_models.Block.Content.File.State.DONE,
                name = name
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should return UpdateBlockFile event with nullable values`() {

        val context = MockDataFactory.randomUuid()
        val id = MockDataFactory.randomUuid()

        val msg = aparok.Event.Block.Set.File(id = id)

        val message = aparok.Event.Message(blockSetFile = msg)

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.UpdateFileBlock(
                context = context,
                blockId = id
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should return granular change with background colour`() {

        val context = MockDataFactory.randomUuid()
        val id = MockDataFactory.randomUuid()
        val color = MockDataFactory.randomString()

        val msg = aparok.Event.Block.Set.BackgroundColor(id = id, backgroundColor = color)

        val message = aparok.Event.Message(blockSetBackgroundColor = msg)

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.GranularChange(
                context = context,
                id = id,
                backgroundColor = color
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should return update-details event`() {

        val context = MockDataFactory.randomUuid()

        val id = MockDataFactory.randomUuid()

        val icon = Pair("icon", ":package:")
        val name = Pair("name", "Document I")

        val details = mutableMapOf(icon, name)

        val msg = aparok.Event.Object.Details.Set(
            id = id,
            details = mapOf(
                icon.first to icon.second,
                name.first to name.second
            )
        )

        val message = aparok.Event.Message(objectDetailsSet = msg)

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.Details.Set(
                context = context,
                target = id,
                details = details
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }

    @Test
    fun `should return remove relation links event`() {

        val context = MockDataFactory.randomUuid()

        val id = MockDataFactory.randomUuid()

        val id1 = MockDataFactory.randomUuid()
        val id2 = MockDataFactory.randomUuid()
        val id3 = MockDataFactory.randomUuid()

        val msg = aparok.Event.Object.Relations.Remove(
            id = id,
            relationKeys = listOf(id1, id2, id3)
        )

        val message = aparok.Event.Message(objectRelationsRemove = msg)

        val event = aparok.Event(contextId = context, messages = listOf(message))

        proxy.stub {
            on { flow(EventGroup.EDITOR) } doReturn flowOf(event)
        }

        val expected = listOf(
            Event.Command.ObjectRelation.Remove(
                context = context,
                id = id,
                keys = listOf(id1, id2, id3)
            )
        )

        runBlocking {
            channel.observeEvents(context = context).collect { events ->
                assertEquals(
                    expected = expected,
                    actual = events
                )
            }
        }
    }
}