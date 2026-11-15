package com.aparokio.aparok.di.feature

import android.content.Context
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.debugging.DebugExportLogs
import com.aparokio.aparok.domain.device.PathProvider
import com.aparokio.aparok.domain.networkmode.GetNetworkMode
import com.aparokio.aparok.domain.networkmode.SetNetworkMode
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.settings.PreferencesViewModel
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.networkModeCopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import com.aparokio.aparok.ui.onboarding.OnboardingNetworkSetupDialog
import com.aparokio.aparok.ui.settings.system.PreferenceFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [AppPreferencesDependencies::class],
    modules = [AppPreferencesModule::class]
)
@PerScreen
interface AppPreferencesComponent {

    @Component.Factory
    interface Factory {
        fun create(dependency: AppPreferencesDependencies): AppPreferencesComponent
    }

    fun inject(fragment: PreferenceFragment)
    fun inject(fragment: OnboardingNetworkSetupDialog)
}

@Module
object AppPreferencesModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideCopyFileToCache(
        context: Context
    ): CopyFileToCacheDirectory = networkModeCopyFileToCacheDirectory(context)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideLogsExport(
        repository: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): DebugExportLogs = DebugExportLogs(repository, dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideViewModelFactory(
        copyFileToCacheDirectory: CopyFileToCacheDirectory,
        getNetworkMode: GetNetworkMode,
        setNetworkMode: SetNetworkMode,
        analytics: Analytics,
        debugExportLogs: DebugExportLogs,
        pathProvider: PathProvider,
        urlFileProvider: UriFileProvider
    ): PreferencesViewModel.Factory = PreferencesViewModel.Factory(
        copyFileToCacheDirectory = copyFileToCacheDirectory,
        getNetworkMode = getNetworkMode,
        setNetworkMode = setNetworkMode,
        analytics = analytics,
        debugExportLogs = debugExportLogs,
        pathProvider = pathProvider,
        uriFileProvider = urlFileProvider
    )
}

interface AppPreferencesDependencies : ComponentDependencies {
    fun context(): Context
    fun dispatchers(): AppCoroutineDispatchers
    fun authRepository(): AuthRepository
    fun analytics(): Analytics
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
    fun pathProvider(): PathProvider
    fun provideUriFileProvider(): UriFileProvider
}