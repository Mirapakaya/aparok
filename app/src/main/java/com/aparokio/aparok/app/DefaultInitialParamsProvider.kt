package com.aparokio.aparok.app

import android.content.Context
import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.platform.InitialParamsProvider

class DefaultInitialParamsProvider(context: Context) : InitialParamsProvider {

    override val workDir = context.filesDir.absolutePath
    override val defaultLogLevel =
        "common.commonspace.headsync=INFO;core.block.editor.spaceview=INFO;*=WARN"

    override fun getVersion(): String {
        return if (BuildConfig.DEBUG)
            BuildConfig.VERSION_NAME + DEV_PREFIX
        else {
            return BuildConfig.VERSION_NAME
        }
    }

    override fun getPlatform(): String = PLATFORM_NAME

    companion object {
        const val PLATFORM_NAME = "android"
        const val DEV_PREFIX = "-dev"
    }

    override fun toCommand() = Command.SetInitialParams(
        version = getVersion(),
        platform = getPlatform(),
        workDir = workDir,
        defaultLogLevel = defaultLogLevel
    )
}