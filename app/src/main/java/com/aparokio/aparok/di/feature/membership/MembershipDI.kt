package com.aparokio.aparok.di.feature.membership

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.payments.GetMembershipCodeInfo
import com.aparokio.aparok.domain.payments.GetMembershipEmailStatus
import com.aparokio.aparok.domain.payments.GetMembershipPaymentUrl
import com.aparokio.aparok.domain.payments.IsMembershipNameValid
import com.aparokio.aparok.domain.payments.RedeemMembershipCode
import com.aparokio.aparok.domain.payments.SetMembershipEmail
import com.aparokio.aparok.domain.payments.VerifyMembershipEmailCode
import com.aparokio.aparok.payments.playbilling.BillingClientLifecycle
import com.aparokio.aparok.ui.payments.MembershipFragment
import com.aparokio.aparok.payments.viewmodel.MembershipViewModelFactory
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [MembershipComponentDependencies::class],
    modules = [
        MembershipModule::class,
        MembershipModule.Declarations::class
    ]
)
@PerScreen
interface MembershipComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MembershipComponentDependencies): MembershipComponent
    }

    fun inject(fragment: MembershipFragment)
}

@Module
object MembershipModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetAccountUseCase(
        repo: AuthRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetAccount = GetAccount(repo = repo, dispatcher = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetPaymentsUrl(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetMembershipPaymentUrl = GetMembershipPaymentUrl(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideIsNameValid(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): IsMembershipNameValid = IsMembershipNameValid(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetEmailStatus(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetMembershipEmailStatus = GetMembershipEmailStatus(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideSetMembershipEmail(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): SetMembershipEmail = SetMembershipEmail(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideVerifyEmailCode(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): VerifyMembershipEmailCode = VerifyMembershipEmailCode(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideGetMembershipCodeInfo(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): GetMembershipCodeInfo = GetMembershipCodeInfo(repo = repo, dispatchers = dispatchers)

    @JvmStatic
    @Provides
    @PerScreen
    fun provideRedeemMembershipCode(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): RedeemMembershipCode = RedeemMembershipCode(repo = repo, dispatchers = dispatchers)

    @Module
    interface Declarations {

        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: MembershipViewModelFactory
        ): ViewModelProvider.Factory

    }
}

interface MembershipComponentDependencies : ComponentDependencies {
    fun analytics(): Analytics
    fun context(): Context
    fun billingListener(): BillingClientLifecycle
    fun authRepository(): AuthRepository
    fun blockRepository(): BlockRepository
    fun appCoroutineDispatchers(): AppCoroutineDispatchers
    fun provideMembershipProvider(): MembershipProvider
}