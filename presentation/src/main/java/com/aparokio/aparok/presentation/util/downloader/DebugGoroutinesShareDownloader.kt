package com.aparokio.aparok.presentation.util.downloader

import android.content.Context
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DebugGoroutinesShareDownloader @Inject constructor(
    private val repo: BlockRepository,
    context: Context,
    uriFileProvider: UriFileProvider,
    dispatchers: AppCoroutineDispatchers
) : MiddlewareShareDownloader(context, uriFileProvider, dispatchers) {

    override suspend fun downloadFile(hash: String, path: String): String {
        repo.debugStackGoroutines(path = path)
        return path
    }
}