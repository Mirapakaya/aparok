package com.aparokio.aparok.di.feature.notifications

import android.content.Context
import com.aparokio.aparok.device.AparokPushService
import com.aparokio.aparok.device.DefaultPushMessageProcessor
import com.aparokio.aparok.device.PushMessageProcessor
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.main.ConfigModule.DEFAULT_APP_COROUTINE_SCOPE
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.device.DeviceTokenStoringService
import com.aparokio.aparok.domain.notifications.NotificationBuilder
import com.aparokio.aparok.domain.resources.StringResourceProvider
import com.aparokio.aparok.presentation.notifications.CryptoService
import com.aparokio.aparok.presentation.notifications.CryptoServiceImpl
import com.aparokio.aparok.presentation.notifications.DecryptionPushContentService
import com.aparokio.aparok.presentation.notifications.DecryptionPushContentServiceImpl
import com.aparokio.aparok.presentation.notifications.SignatureVerificationService
import com.aparokio.aparok.presentation.notifications.SignatureVerificationServiceImpl
import com.aparokio.aparok.domain.notifications.PushKeyProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

@Singleton
@Component(
    dependencies = [PushContentDependencies::class],
    modules = [PushContentModule::class],
)
interface PushContentComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: PushContentDependencies): PushContentComponent
    }

    fun inject(service: AparokPushService)
}

@Module
object PushContentModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providePushMessageProcessor(
        decryptionService: DecryptionPushContentService,
        notificationBuilder: NotificationBuilder
    ): PushMessageProcessor = DefaultPushMessageProcessor(
        decryptionService = decryptionService,
        notificationBuilder = notificationBuilder
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideCryptoService(): CryptoService = CryptoServiceImpl()

    @JvmStatic
    @Provides
    @Singleton
    fun provideSignatureVerificationService(): SignatureVerificationService = SignatureVerificationServiceImpl()

    @JvmStatic
    @Provides
    @Singleton
    fun provideDecryptionPushContentService(
        pushKeyProvider: PushKeyProvider,
        cryptoService: CryptoService,
        signatureVerificationService: SignatureVerificationService,
        json: Json
    ): DecryptionPushContentService = DecryptionPushContentServiceImpl(
        pushKeyProvider = pushKeyProvider,
        cryptoService = cryptoService,
        signatureVerificationService = signatureVerificationService,
        json = json
    )
}

interface PushContentDependencies : ComponentDependencies {
    fun deviceTokenSavingService(): DeviceTokenStoringService
    fun pushKeyProvider(): PushKeyProvider
    fun context(): Context
    @Named(DEFAULT_APP_COROUTINE_SCOPE) fun scope(): CoroutineScope
    fun dispatchers(): AppCoroutineDispatchers
    fun provider(): StringResourceProvider
    fun notificationBuilder(): NotificationBuilder
    fun json(): Json
    fun authRepository(): AuthRepository
}