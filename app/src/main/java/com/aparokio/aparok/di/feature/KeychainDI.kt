package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.domain.auth.interactor.GetMnemonic
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.presentation.keychain.KeychainPhraseViewModelFactory
import com.aparokio.aparok.ui.dashboard.DashboardMnemonicReminderDialog
import com.aparokio.aparok.ui.profile.KeychainPhraseDialog
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Subcomponent(
    modules = [KeychainPhraseModule::class]
)
@PerScreen
interface KeychainPhraseSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun keychainPhraseModule(module: KeychainPhraseModule): Builder
        fun build(): KeychainPhraseSubComponent
    }

    fun inject(fragment: KeychainPhraseDialog)
    fun inject(fragment: DashboardMnemonicReminderDialog)
}

@Module
object KeychainPhraseModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideKeychainPhraseViewModelFactory(
        getMnemonic: GetMnemonic,
        analytics: Analytics
    ) = KeychainPhraseViewModelFactory(
        getMnemonic = getMnemonic,
        analytics = analytics
    )

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetMnemonicUseCase(
        repository: AuthRepository
    ): GetMnemonic = GetMnemonic(
        repository = repository
    )
}