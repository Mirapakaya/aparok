package com.aparokio.aparok.device

import android.content.Context
import com.aparokio.aparok.domain.device.PathProvider

class DefaultPathProvider(
    private val context: Context
) : PathProvider {
    override fun providePath(): String = context.filesDir.path
    override fun cachePath(): String = context.cacheDir.path
}