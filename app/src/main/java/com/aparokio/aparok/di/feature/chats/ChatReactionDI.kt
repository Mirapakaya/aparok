package com.aparokio.aparok.di.feature.chats

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.ActiveSpaceMemberSubscriptionContainer
import com.aparokio.aparok.feature_chats.presentation.ChatReactionViewModel
import com.aparokio.aparok.ui.chats.ChatReactionFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    dependencies = [ChatReactionDependencies::class],
    modules = [
        ChatReactionModule::class,
        ChatReactionModule.Declarations::class
    ]
)
@PerScreen
interface ChatReactionComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withParams(params: ChatReactionViewModel.Params): Builder
        fun withDependencies(dependencies: ChatReactionDependencies): Builder
        fun build(): ChatReactionComponent
    }

    fun inject(fragment: ChatReactionFragment)
}

@Module
object ChatReactionModule {

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: ChatReactionViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface ChatReactionDependencies : ComponentDependencies {
    fun dispatchers(): AppCoroutineDispatchers
    fun repo(): BlockRepository
    fun auth(): AuthRepository
    fun urlBuilder(): UrlBuilder
    fun members(): ActiveSpaceMemberSubscriptionContainer
}