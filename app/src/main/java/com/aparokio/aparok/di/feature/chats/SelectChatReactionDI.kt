package com.aparokio.aparok.di.feature.chats

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.emojifier.data.Emoji
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.feature_chats.presentation.SelectChatReactionViewModel
import com.aparokio.aparok.ui.chats.SelectChatReactionFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [SelectChatReactionDependencies::class],
    modules = [
        SelectChatReactionModule::class,
        SelectChatReactionModule.Declarations::class
    ]
)
@PerScreen
interface SelectChatReactionComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withParams(params: SelectChatReactionViewModel.Params): Builder
        fun withDependencies(dependencies: SelectChatReactionDependencies): Builder
        fun build(): SelectChatReactionComponent
    }

    fun getViewModel(): SelectChatReactionViewModel
    fun inject(fragment: SelectChatReactionFragment)
}

@Module
object SelectChatReactionModule {
    @Provides
    @PerScreen
    @JvmStatic
    fun provideEmojiProvider(): EmojiProvider = Emoji

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: SelectChatReactionViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface SelectChatReactionDependencies : ComponentDependencies {
    fun dispatchers(): AppCoroutineDispatchers
    fun suggester(): EmojiSuggester
    fun repo(): BlockRepository
    fun auth(): AuthRepository
    fun prefs(): UserSettingsRepository
}