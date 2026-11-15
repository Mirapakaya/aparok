package com.aparokio.aparok.domain.auth

import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.interactor.Logout
import com.aparokio.aparok.domain.auth.interactor.PreloadRemainingSpaces
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.common.DefaultCoroutineTestRule
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.launch.RemainingSpacesPreloader
import com.aparokio.aparok.domain.workspace.SpaceManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class LogoutTest {

    @get:Rule
    val rule = DefaultCoroutineTestRule()

    @Mock lateinit var repo: AuthRepository
    @Mock lateinit var config: ConfigStorage
    @Mock lateinit var user: UserSettingsRepository
    @Mock lateinit var spaceManager: SpaceManager
    @Mock lateinit var awaitAccountStartManager: AwaitAccountStartManager
    @Mock lateinit var preferredSpaceIdHolder: PreferredSpaceIdHolder

    private lateinit var logout: Logout

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val dispatchers = AppCoroutineDispatchers(
            io = rule.dispatcher,
            computation = rule.dispatcher,
            main = rule.dispatcher
        )
        logout = Logout(
            repo = repo,
            config = config,
            user = user,
            spaceManager = spaceManager,
            awaitAccountStartManager = awaitAccountStartManager,
            remainingSpacesPreloader = RemainingSpacesPreloader(
                PreloadRemainingSpaces(dispatchers, repo)
            ),
            preferredSpaceIdHolder = preferredSpaceIdHolder,
            dispatchers = dispatchers
        )
    }

    @Test
    fun `clears preferred space id holder on logout`() = runTest {
        logout(Logout.Params()).collect {}
        verify(preferredSpaceIdHolder).clear()
    }
}
