package com.aparokio.aparok.domain.auth

import com.aparokio.aparok.core_models.CoroutineTestRule
import com.aparokio.aparok.core_models.StubConfig
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.auth.interactor.ResumeAccount
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.platform.InitialParamsProvider
import com.aparokio.aparok.domain.workspace.SpaceManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ResumeAccountTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutineTestRule()

    @Mock
    lateinit var repo: AuthRepository

    @Mock
    lateinit var configStorage: ConfigStorage

    @Mock
    lateinit var spaceManager: SpaceManager

    @Mock
    lateinit var userSettingsRepository: UserSettingsRepository

    @Mock
    lateinit var pathProvider: PathProvider

    @Mock
    lateinit var initialParamsProvider: InitialParamsProvider

    @Mock
    lateinit var awaitAccountStartManager: AwaitAccountStartManager

    lateinit var resumeAccount: ResumeAccount

    private val config = StubConfig()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        resumeAccount = ResumeAccount(
            repository = repo,
            configStorage = configStorage,
            pathProvider = pathProvider,
            initialParamsProvider = initialParamsProvider,
            awaitAccountStartManager = awaitAccountStartManager,
            spaceManager = spaceManager,
            settings = userSettingsRepository
        )
    }
}