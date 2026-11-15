package com.aparokio.aparok.providers

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.presentation.util.downloader.UriFileProvider
import java.io.File
import javax.inject.Inject

class DefaultUriFileProvider @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : UriFileProvider {

    override fun getUriForFile(file: File): Uri {
        logger.logInfo("DefaultUriFileProvider, start getting uri for file $file")
        val contentUri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + PROVIDER,
            file
        )
        logger.logInfo("DefaultUriFileProvider, got uri $contentUri")
        return contentUri
    }
}

private const val PROVIDER = ".provider"