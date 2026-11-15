package com.aparokio.aparok.di.main

import android.content.Context
import com.aparokio.aparok.data.auth.other.DataDownloader
import com.aparokio.aparok.data.auth.other.Device
import com.aparokio.aparok.device.providers.DefaultLocalProvider
import com.aparokio.aparok.device.SharedFileUploader
import com.aparokio.aparok.device.base.AndroidDevice
import com.aparokio.aparok.device.download.AndroidDeviceDownloader
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.device.FileSharer
import com.aparokio.aparok.domain.download.Downloader
import com.aparokio.aparok.domain.misc.LocaleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DeviceModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDownloader(
        device: Device
    ): Downloader = DataDownloader(device = device)

    @JvmStatic
    @Provides
    @Singleton
    fun provideDevice(
        downloader: AndroidDeviceDownloader
    ): Device = AndroidDevice(downloader = downloader)

    @JvmStatic
    @Provides
    @Singleton
    fun provideDeviceDownloader(
        context: Context
    ): AndroidDeviceDownloader = AndroidDeviceDownloader(context = context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideLocaleProvider(context: Context): LocaleProvider = DefaultLocalProvider(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideFileSharer(
        context: Context,
        dispatchers: AppCoroutineDispatchers
    ): FileSharer = SharedFileUploader(
        context = context,
        dispatchers = dispatchers
    )
}