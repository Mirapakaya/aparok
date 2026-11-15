package com.aparokio.aparok.domain.device

import com.aparokio.aparok.core_models.DeviceNetworkType

interface NetworkConnectionStatus {
    fun start()
    fun stop()
    fun getCurrentNetworkType(): DeviceNetworkType
}