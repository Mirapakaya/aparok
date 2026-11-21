package com.aparokio.aparok.data.auth.other

import com.aparokio.aparok.core_models.Url
import com.aparokio.aparok.domain.download.Downloader

class DataDownloader(private val device: Device) : Downloader {

    override fun download(url: Url, name: String) = device.download(url, name)
}