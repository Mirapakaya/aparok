package com.aparokio.aparok.di.main

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.analytics.base.DefaultAnalytics
import com.aparokio.aparok.analytics.tracker.AmplitudeTracker
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.analytics.DefaultAnalyticsParamsProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import javax.inject.Singleton

@Module
object AnalyticsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideAnalytics(): Analytics = DefaultAnalytics()

    @JvmStatic
    @Provides
    @Singleton
    fun provideAmplitudeTracker(
        analytics: Analytics
    ): AmplitudeTracker = AmplitudeTracker(
        scope = GlobalScope,
        analytics = analytics
    )

    @JvmStatic
    @Provides
    @Singleton
    fun provideAnalyticsParams(
        userPermissionProvider: UserPermissionProvider,
        spaceViewContainer: SpaceViewSubscriptionContainer
    ): AnalyticSpaceHelperDelegate = DefaultAnalyticsParamsProvider(
        userPermissionProvider = userPermissionProvider,
        spaceViewContainer = spaceViewContainer
    )
}