package com.aparokio.aparok.di.feature.chats

import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.chats.ChatContainer
import com.aparokio.aparok.domain.chats.ChatEventChannel
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope

/** Shared by chats and discussions, which both collect a ChatContainer. */
@Module
object ChatContainerModule {
    @JvmStatic
    @Provides
    @PerScreen
    fun provideChatContainer(
        repo: BlockRepository,
        channel: ChatEventChannel,
        logger: Logger,
        subscription: StorelessSubscriptionContainer,
        @Named(DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope,
        dispatchers: AppCoroutineDispatchers
    ): ChatContainer = ChatContainer(
        repo, channel, logger, subscription,
        readScope = CoroutineScope(scope.coroutineContext + dispatchers.io)
    )
}
