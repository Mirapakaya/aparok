package com.aparokio.aparok.presentation.onboarding.signup

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.DeviceNetworkType
import com.aparokio.aparok.core_models.NetworkMode
import com.aparokio.aparok.core_models.NetworkModeConfig
import com.aparokio.aparok.domain.auth.interactor.GetMnemonic
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.device.NetworkConnectionStatus
import com.aparokio.aparok.domain.network.NetworkModeProvider
import com.aparokio.aparok.presentation.util.DefaultCoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.stub

class OnboardingMnemonicViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = DefaultCoroutineTestRule()

    @Mock
    private lateinit var getMnemonic: GetMnemonic

    @Mock
    private lateinit var analytics: Analytics

    @Mock
    private lateinit var networkConnectionStatus: NetworkConnectionStatus

    @Mock
    private lateinit var networkModeProvider: NetworkModeProvider

    lateinit var pendingIntentStore: PendingIntentStore

    @Before
    fun setup() {
        pendingIntentStore = PendingIntentStore()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `shouldShowEmail returns false when network is not connected`() {
        // Given
        networkConnectionStatus.stub {
            on { getCurrentNetworkType() }.thenReturn(DeviceNetworkType.NOT_CONNECTED)
        }
        networkModeProvider.stub {
            on { get() }.thenReturn(
                NetworkModeConfig(
                    networkMode = NetworkMode.DEFAULT
                )
            )
        }

        // When
        val viewModel = createViewModel()
        val result = viewModel.shouldShowEmail()

        // Then
        assertFalse(result)
    }

    @Test
    fun `shouldShowEmail returns false when network mode is LOCAL`() {
        // Given
        networkConnectionStatus.stub {
            on { getCurrentNetworkType() }.thenReturn(DeviceNetworkType.WIFI)
        }
        networkModeProvider.stub {
            on { get() }.thenReturn(
                NetworkModeConfig(
                    networkMode = NetworkMode.LOCAL
                )
            )
        }

        // When
        val viewModel = createViewModel()
        val result = viewModel.shouldShowEmail()

        // Then
        assertFalse(result)
    }

    @Test
    fun `shouldShowEmail returns true when connected to network and network mode is not LOCAL`() {
        // Given
        networkConnectionStatus.stub {
            on { getCurrentNetworkType() }.thenReturn(DeviceNetworkType.WIFI)
        }
        networkModeProvider.stub {
            on { get() }.thenReturn(
                NetworkModeConfig(
                    networkMode = NetworkMode.DEFAULT
                )
            )
        }

        // When
        val viewModel = createViewModel()
        val result = viewModel.shouldShowEmail()

        // Then
        assertTrue(result)
    }

    @Test
    fun `shouldShowEmail returns true with cellular network and non-LOCAL mode`() {
        // Given
        networkConnectionStatus.stub {
            on { getCurrentNetworkType() }.thenReturn(DeviceNetworkType.CELLULAR)
        }
        networkModeProvider.stub {
            on { get() }.thenReturn(
                NetworkModeConfig(
                    networkMode = NetworkMode.DEFAULT
                )
            )
        }

        // When
        val viewModel = createViewModel()
        val result = viewModel.shouldShowEmail()

        // Then
        assertTrue(result)
    }

    private fun createViewModel(): OnboardingMnemonicViewModel {
        return OnboardingMnemonicViewModel(
            getMnemonic = getMnemonic,
            analytics = analytics,
            networkModeProvider = networkModeProvider,
            networkConnectionStatus = networkConnectionStatus
        )
    }
} 