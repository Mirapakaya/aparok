package com.aparokio.aparok.di.main

import android.content.Context
import com.aparokio.aparok.CrashReporter
import com.aparokio.aparok.SentryCrashReporter
import com.aparokio.aparok.core_utils.tools.AppInfo
import com.aparokio.aparok.presentation.device.BuildProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CrashReportingModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideCrashReporter(
        context: Context,
        appInfo: AppInfo,
        buildProvider: BuildProvider
    ): CrashReporter = SentryCrashReporter(
        context = context,
        appInfo = appInfo,
        withTimber = !buildProvider.isDebug()
    )
}