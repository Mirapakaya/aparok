package com.aparokio.aparok.di.main


import android.content.Context
import com.aparokio.aparok.domain.icon.DocumentEmojiIconProvider
import com.aparokio.aparok.emojifier.data.DefaultDocumentEmojiIconProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.emojifier.suggest.data.DefaultEmojiSuggestStorage
import com.aparokio.aparok.emojifier.suggest.data.DefaultEmojiSuggester
import com.aparokio.aparok.emojifier.suggest.data.EmojiSuggestStorage
import com.aparokio.aparok.emojifier.suggest.data.EmojiSuggesterCache
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object EmojiModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideEmojiSuggester(
        cache: EmojiSuggesterCache,
        storage: EmojiSuggestStorage
    ): EmojiSuggester {
        return DefaultEmojiSuggester(
            cache = cache,
            storage = storage
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideEmojiSuggesterCache(): EmojiSuggesterCache {
        return EmojiSuggesterCache.DefaultCache()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideEmojiSuggestStorage(context: Context, gson: Gson): EmojiSuggestStorage {
        return DefaultEmojiSuggestStorage(context, gson)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideDocumentEmojiIconProvider(): DocumentEmojiIconProvider {
        return DefaultDocumentEmojiIconProvider()
    }
}