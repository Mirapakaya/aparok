package com.aparokio.aparok.di.feature.chats

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.emojifier.data.Emoji
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.feature_chats.presentation.SelectChatIconViewModel
import com.aparokio.aparok.ui.chats.SelectChatIconFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [SelectChatIconDependencies::class],
    modules = [
        SelectChatIconModule::class,
        SelectChatIconModule.Declarations::class
    ]
)
@PerScreen
interface SelectChatIconComponent {
    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: SelectChatIconDependencies): Builder
        fun build(): SelectChatIconComponent
    }

    fun getViewModel(): SelectChatIconViewModel
    fun inject(fragment: SelectChatIconFragment)
}

@Module
object SelectChatIconModule {
    @Provides
    @PerScreen
    @JvmStatic
    fun provideEmojiProvider(): EmojiProvider = Emoji

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: SelectChatIconViewModel.Factory
        ): ViewModelProvider.Factory
    }
}

interface SelectChatIconDependencies : ComponentDependencies {
    fun dispatchers(): AppCoroutineDispatchers
    fun suggester(): EmojiSuggester
}
