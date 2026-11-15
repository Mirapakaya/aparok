package com.aparokio.aparok.di.main

import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import com.aparokio.aparok.clipboard.AparokClipboard
import com.aparokio.aparok.clipboard.AparokClipboardStorage
import com.aparokio.aparok.clipboard.AparokUriMatcher
import com.aparokio.aparok.data.auth.mapper.Serializer
import com.aparokio.aparok.data.auth.other.ClipboardDataUriMatcher
import com.aparokio.aparok.data.auth.repo.clipboard.ClipboardDataRepository
import com.aparokio.aparok.data.auth.repo.clipboard.ClipboardDataStore
import com.aparokio.aparok.domain.clipboard.Clipboard
import com.aparokio.aparok.middleware.converters.ClipboardSerializer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ClipboardModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideClipboardRepository(
        factory: ClipboardDataStore.Factory
    ) : Clipboard = ClipboardDataRepository(factory)

    @JvmStatic
    @Provides
    @Singleton
    fun provideClipboardDataStoreFactory(
        storage: ClipboardDataStore.Storage,
        system: ClipboardDataStore.System
    ) : ClipboardDataStore.Factory = ClipboardDataStore.Factory(storage, system)

    @JvmStatic
    @Provides
    @Singleton
    fun provideClipboardStorage(
        context: Context,
        serializer: Serializer
    ) : ClipboardDataStore.Storage = AparokClipboardStorage(context, serializer)

    @JvmStatic
    @Provides
    @Singleton
    fun provideClipboardSystem(
        cm: ClipboardManager
    ) : ClipboardDataStore.System = AparokClipboard(cm)

    @JvmStatic
    @Provides
    @Singleton
    fun provideClipboardManager(
        context: Context
    ) : ClipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

    @JvmStatic
    @Provides
    @Singleton
    fun provideUriMatcher() : Clipboard.UriMatcher = ClipboardDataUriMatcher(
        matcher = AparokUriMatcher()
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideSerializer() : Serializer = ClipboardSerializer()
}