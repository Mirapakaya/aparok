package com.aparokio.aparok.domain.platform

import com.aparokio.aparok.core_models.Command

interface InitialParamsProvider {
    fun getVersion(): String
    fun getPlatform(): String
    val workDir: String
    val defaultLogLevel: String
    fun toCommand(): Command.SetInitialParams
}