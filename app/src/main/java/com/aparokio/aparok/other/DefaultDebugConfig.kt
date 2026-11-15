package com.aparokio.aparok.other

import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.domain.debugging.DebugConfig
import javax.inject.Inject

class DefaultDebugConfig @Inject constructor(): DebugConfig {
    override val traceSubscriptions: Boolean get() = BuildConfig.DEBUG
    override val setTimeouts: Boolean get() = BuildConfig.DEBUG
}