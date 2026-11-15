package com.aparokio.aparok.di.main

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.app.DefaultFeatureToggles
import com.aparokio.aparok.app.TogglePrefs
import com.aparokio.aparok.core_utils.tools.AppInfo
import com.aparokio.aparok.core_utils.tools.DefaultAppInfo
import com.aparokio.aparok.core_utils.tools.DefaultThreadInfo
import com.aparokio.aparok.core_utils.tools.DefaultUrlValidator
import com.aparokio.aparok.core_utils.tools.FeatureToggles
import com.aparokio.aparok.core_utils.tools.ThreadInfo
import com.aparokio.aparok.core_utils.tools.UrlValidator
import com.aparokio.aparok.device.providers.AppDefaultDateFormatProvider
import com.aparokio.aparok.device.providers.DateProviderImpl
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.debugging.DebugConfig
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.DateTypeNameProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.objects.GetDateObjectByTimestamp
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.primitives.FieldParserImpl
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.middleware.interactor.MiddlewareProtobufLogger
import com.aparokio.aparok.middleware.interactor.ProtobufConverterProvider
import com.aparokio.aparok.other.BasicLogger
import com.aparokio.aparok.other.DefaultDateTypeNameProvider
import com.aparokio.aparok.other.DefaultDebugConfig
import com.aparokio.aparok.presentation.util.StringResourceProviderImpl
import com.aparokio.aparok.presentation.util.UrlHelper
import com.aparokio.aparok.presentation.widgets.collection.ResourceProvider
import com.aparokio.aparok.presentation.widgets.collection.ResourceProviderImpl
import com.aparokio.aparok.util.UrlHelperImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.time.ZoneId
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module(includes = [UtilModule.Bindings::class])
object UtilModule {

    @JvmStatic
    @Provides
    @Singleton
    @TogglePrefs
    fun providesSharedPreferences(
        context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppInfo(): AppInfo = DefaultAppInfo(BuildConfig.VERSION_NAME)

    @JvmStatic
    @Provides
    @Singleton
    fun provideDateProvider(
        localeProvider: LocaleProvider,
        appDefaultDateFormatProvider: AppDefaultDateFormatProvider,
        stringResourceProvider: StringResourceProvider
    ): DateProvider = DateProviderImpl(
        defaultZoneId = ZoneId.systemDefault(),
        localeProvider = localeProvider,
        appDefaultDateFormatProvider = appDefaultDateFormatProvider,
        stringResourceProvider = stringResourceProvider
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideDateByTimestamp(
        repository: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetDateObjectByTimestamp = GetDateObjectByTimestamp(repository, dispatchers)

    @JvmStatic
    @Provides
    @Singleton
    fun provideFieldsProvider(
        dateProvider: DateProvider,
        logger: Logger,
        getDateObjectByTimestamp: GetDateObjectByTimestamp,
        stringResourceProvider: StringResourceProvider
    ): FieldParser = FieldParserImpl(dateProvider, logger, getDateObjectByTimestamp, stringResourceProvider)

    @JvmStatic
    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider =
        ResourceProviderImpl(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideStringResourceProvider(context: Context): StringResourceProvider =
        StringResourceProviderImpl(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @JvmStatic
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideUrlHelper(): UrlHelper = UrlHelperImpl()

    @Module
    interface Bindings {

        @Binds
        @Singleton
        fun bindLogger(logger: BasicLogger): Logger

        @Binds
        @Singleton
        fun bindDebugConfig(config: DefaultDebugConfig): DebugConfig

        @Binds
        @Singleton
        fun bindThreadInfo(info: DefaultThreadInfo): ThreadInfo

        @Binds
        @Singleton
        fun bindUrlValidator(applicator: DefaultUrlValidator): UrlValidator

        @Binds
        @Singleton
        fun bindFeatureToggles(applicator: DefaultFeatureToggles): FeatureToggles

        @Binds
        @Singleton
        fun bindMiddlewareProtobufLogger(logger: MiddlewareProtobufLogger.Impl): MiddlewareProtobufLogger

        @Binds
        @Singleton
        fun bindProtobufConverterProvider(provider: ProtobufConverterProvider.Impl): ProtobufConverterProvider

        @Binds
        @Singleton
        fun bindDateTypeNameProvider(provider: DefaultDateTypeNameProvider): DateTypeNameProvider
    }
}