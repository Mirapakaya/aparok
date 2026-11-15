package com.aparokio.aparok.di.feature.settings

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.presentation.device.BuildProvider
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.account.DeleteAccount
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.device.ClearFileCache
import com.aparokio.aparok.domain.download.FileAutoDownloadSetLimit
import com.aparokio.aparok.domain.download.FileSetAutoDownload
import com.aparokio.aparok.domain.workspace.FileLimitsEventChannel
import com.aparokio.aparok.domain.workspace.InterceptFileLimitEvents
import com.aparokio.aparok.domain.workspace.SpacesUsageInfo
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.settings.FilesStorageViewModel
import com.aparokio.aparok.ui.settings.FilesStorageFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@PerScreen
@Component(
    dependencies = [FilesStorageDependencies::class],
    modules = [
        FilesStorageModule::class,
        FilesStorageModule.Declarations::class
    ]
)
interface FilesStorageComponent {

    @Component.Builder
    interface Builder {

        fun withDependencies(dependency: FilesStorageDependencies): Builder
        fun build(): FilesStorageComponent
    }

    fun inject(fragment: FilesStorageFragment)
}

@Module
object FilesStorageModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun clearFileCache(repo: BlockRepository): ClearFileCache = ClearFileCache(repo)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSpaceUsage(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SpacesUsageInfo = SpacesUsageInfo(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideFileSetAutoDownload(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): FileSetAutoDownload = FileSetAutoDownload(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideFileAutoDownloadSetLimit(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): FileAutoDownloadSetLimit = FileAutoDownloadSetLimit(repo, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideFileLimitEvents(
        channel: FileLimitsEventChannel,
        dispatchers: AppCoroutineDispatchers
    ): InterceptFileLimitEvents = InterceptFileLimitEvents(channel, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDeleteAccountUseCase(
        repo: AuthRepository
    ): DeleteAccount = DeleteAccount(repo = repo)

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(factory: FilesStorageViewModel.Factory): ViewModelProvider.Factory
    }
}

interface FilesStorageDependencies : ComponentDependencies {
    fun blockRepo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun analytics(): Analytics
    fun fileEventsChannel(): FileLimitsEventChannel
    fun buildProvider(): BuildProvider
    fun authRepo(): AuthRepository
    fun logger(): Logger
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun userSettingsRepository(): UserSettingsRepository
}