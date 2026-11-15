package com.aparokio.aparok.di.feature.discussions

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.feature.chats.ChatContainerModule
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.feature_discussions.presentation.DiscussionViewModel
import com.aparokio.aparok.feature_discussions.presentation.DiscussionViewModelFactory
import com.aparokio.aparok.presentation.util.CopyFileToCacheDirectory
import com.aparokio.aparok.presentation.util.defaultCopyFileToCacheDirectory
import com.aparokio.aparok.ui.discussions.DiscussionFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope

@Component(
    dependencies = [DiscussionComponentDependencies::class],
    modules = [
        ChatContainerModule::class,
        DiscussionModule.Declarations::class,
        DiscussionModule.Providers::class
    ]
)
@PerScreen
interface DiscussionComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withParams(params: DiscussionViewModel.Params): Builder
        fun withDependencies(dependencies: DiscussionComponentDependencies): Builder
        fun build(): DiscussionComponent
    }
    fun inject(fragment: DiscussionFragment)
}

@Module
object DiscussionModule {
    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: DiscussionViewModelFactory
        ): ViewModelProvider.Factory
    }

    @Module
    object Providers {
        @JvmStatic
        @Provides
        @PerScreen
        fun provideCopyFileToCache(
            context: Context
        ): CopyFileToCacheDirectory = defaultCopyFileToCacheDirectory(context)
    }
}

interface DiscussionComponentDependencies : ComponentDependencies {
    @Named(DEFAULT_APP_COROUTINE_SCOPE) fun scope(): CoroutineScope
    fun blockRepository(): BlockRepository
    fun authRepo(): AuthRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun urlBuilder(): UrlBuilder
    fun chatEventChannel(): ChatEventChannel
    fun logger(): Logger
    fun members(): ActiveSpaceMemberSubscriptionContainer
    fun storelessSubscriptionContainer(): StorelessSubscriptionContainer
    fun dateProvider(): DateProvider
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun fieldParser(): FieldParser
    fun context(): Context
}
