package com.aparokio.aparok.data.auth.repo.config

import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.config.Gateway
import com.aparokio.aparok.domain.debugging.Logger

class GatewayProvider(
    private val configStorage: ConfigStorage,
    private val logger: Logger
) : Gateway {
    override fun provide(): String {
        val gateway = configStorage.getOrNull()?.gateway
        if (gateway == null) {
            logger.logException(IllegalStateException("Gateway not found"))
        }
        return gateway.orEmpty()
    }

}