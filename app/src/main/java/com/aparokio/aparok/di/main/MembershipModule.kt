package com.aparokio.aparok.di.main

import android.content.Context
import com.aparokio.aparok.data.auth.event.MembershipDateChannel
import com.aparokio.aparok.data.auth.event.MembershipRemoteChannel
import com.aparokio.aparok.domain.account.AwaitAccountStartManager
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.workspace.MembershipChannel
import com.aparokio.aparok.middleware.EventProxy
import com.aparokio.aparok.middleware.interactor.MembershipMiddlewareChannel
import com.aparokio.aparok.payments.playbilling.BillingClientLifecycle
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module
object MembershipModule {

    @Singleton
    @Provides
    fun provideBillingLifecycle(
        context: Context,
        dispatchers: AppCoroutineDispatchers,
        @Named(ConfigModule.DEFAULT_APP_COROUTINE_SCOPE) scope: CoroutineScope
    ): BillingClientLifecycle {
        return BillingClientLifecycle(
            dispatchers = dispatchers,
            applicationContext = context,
            scope = scope
        )
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideMembershipRemoteChannel(
        proxy: EventProxy
    ): MembershipRemoteChannel = MembershipMiddlewareChannel(
        eventsProxy = proxy
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideMembershipChannel(
        channel: MembershipRemoteChannel
    ): MembershipChannel = MembershipDateChannel(
        channel = channel
    )

    @JvmStatic
    @Singleton
    @Provides
    fun provideMembershipProvider(
        dispatchers: AppCoroutineDispatchers,
        awaitAccountStartManager: AwaitAccountStartManager,
        membershipChannel: MembershipChannel,
        localeProvider: LocaleProvider,
        repo: BlockRepository,
        dateProvider: DateProvider
    ): MembershipProvider = MembershipProvider.Default(
        dispatchers = dispatchers,
        membershipChannel = membershipChannel,
        awaitAccountStartManager = awaitAccountStartManager,
        localeProvider = localeProvider,
        repo = repo,
        dateProvider = dateProvider
    )
}