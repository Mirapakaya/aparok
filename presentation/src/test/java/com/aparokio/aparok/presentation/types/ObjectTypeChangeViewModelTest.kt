package com.aparokio.aparok.presentation.types

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.launch.GetDefaultObjectType
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.presentation.util.DefaultCoroutineTestRule
import com.aparokio.aparok.test_utils.MockDataFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

@ExperimentalCoroutinesApi
class ObjectTypeChangeViewModelTest {

    @Mock
    lateinit var blockRepository: BlockRepository

    @Mock
    lateinit var userSettingsRepository: UserSettingsRepository

    @get:Rule
    val coroutineTestRule = DefaultCoroutineTestRule()

    private lateinit var getDefaultObjectType: GetDefaultObjectType

    val spaceId = MockDataFactory.randomUuid()

    @Mock
    lateinit var spaceViews: SpaceViewSubscriptionContainer

    private val dispatchers = AppCoroutineDispatchers(
        io = coroutineTestRule.dispatcher,
        main = coroutineTestRule.dispatcher,
        computation = coroutineTestRule.dispatcher
    )

    @Before
    fun before() {
    }
}