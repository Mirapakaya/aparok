package com.aparokio.aparok.device.base

import com.aparokio.aparok.data.auth.other.Device
import com.aparokio.aparok.device.download.AndroidDeviceDownloader

class AndroidDevice(
    private val downloader: AndroidDeviceDownloader
) : Device {
    override fun download(url: String, name: String) = downloader.download(url = url, name = name)
}