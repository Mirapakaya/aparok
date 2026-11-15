package com.aparokio.aparok.di.main

import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.widgets.WidgetDispatchEvent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DispatcherModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providePayloadDispatcher(): Dispatcher<Payload> = Dispatcher.Default()

    @JvmStatic
    @Provides
    @Singleton
    fun provideWidgetEventDispatcher(): Dispatcher<WidgetDispatchEvent> = Dispatcher.Default()
}