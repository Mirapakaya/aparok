package com.aparokio.aparok.domain.settings

import app.cash.turbine.test
import com.aparokio.aparok.core_models.settings.VaultSettings
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.common.DefaultCoroutineTestRule
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.vault.ObserveVaultSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.stub
import org.mockito.kotlin.verifyNoInteractions

class ObserveVaultSettingsTest {

    @get:Rule
    val rule = DefaultCoroutineTestRule()

    val dispatchers = AppCoroutineDispatchers(
        io = rule.dispatcher,
        computation = rule.dispatcher,
        main = rule.dispatcher
    )

    @Mock
    lateinit var awaitAccountStartManager: AwaitAccountStartManager

    @Mock
    lateinit var logger: Logger

    @Mock
    lateinit var authRepository: AuthRepository

    @Mock
    lateinit var settingsRepository: UserSettingsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should emit default vault settings when getAccount throws an exceptiom`() = runTest {

        awaitAccountStartManager.stub {
            on {
                awaitStart()
            } doReturn flowOf(AwaitAccountStartManager.State.Started)
        }

        authRepository.stub {
            onBlocking {
                getCurrentAccount()
            } doThrow IllegalStateException()
        }

        val useCase = ObserveVaultSettings(
            settings = settingsRepository,
            auth = authRepository,
            logger = logger,
            dispatchers = dispatchers,
            awaitAccountStart = awaitAccountStartManager
        )

        useCase.flow().test {
            assertEquals(
                expected = VaultSettings.default(),
                actual = awaitItem()
            )
            awaitComplete()
        }

        verifyNoInteractions(settingsRepository)
    }
}