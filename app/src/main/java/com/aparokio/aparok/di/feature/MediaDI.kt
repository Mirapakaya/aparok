package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.domain.download.DownloadFile
import com.aparokio.aparok.domain.download.Downloader
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.presentation.media.MediaViewModel
import com.aparokio.aparok.ui.media.MediaActivity
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Component(
    dependencies = [MediaDependencies::class],
    modules = [
        MediaModule::class,
        MediaModule.Declarations::class
    ]
)
@PerScreen
interface MediaComponent {

    @Component.Factory
    interface Factory {
        fun create(dependency: MediaDependencies): MediaComponent
    }

    fun inject(activity: MediaActivity)
}

@Module
object MediaModule {

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun factory(factory: MediaViewModel.Factory): ViewModelProvider.Factory
    }

    @JvmStatic
    @Provides
    @PerScreen
    fun provideDownloadFileUseCase(
        downloader: Downloader
    ): DownloadFile = DownloadFile(
        downloader = downloader,
        context = Dispatchers.IO
    )
}

interface MediaDependencies : ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun repo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun downloader(): Downloader
    fun userSettings(): UserSettingsRepository
}