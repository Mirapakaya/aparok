package com.aparokio.aparok.di.main

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.search.SubscriptionEventChannel
import com.aparokio.aparok.presentation.templates.DefaultObjectTypeTemplatesContainer
import com.aparokio.aparok.presentation.templates.ObjectTypeTemplatesContainer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TemplatesModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideStorelessContainer(
        repo: BlockRepository,
        channel: SubscriptionEventChannel,
        dispatchers: AppCoroutineDispatchers,
        logger: Logger
    ): StorelessSubscriptionContainer =
        StorelessSubscriptionContainer.Impl(repo, channel, dispatchers, logger)

    @JvmStatic
    @Provides
    @Singleton
    fun provideTemplatesContainer(
        storage: StorelessSubscriptionContainer
    ): ObjectTypeTemplatesContainer =
        DefaultObjectTypeTemplatesContainer(
            storage = storage
        )
}