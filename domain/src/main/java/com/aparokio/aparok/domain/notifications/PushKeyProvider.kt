package com.aparokio.aparok.domain.notifications

import com.aparokio.aparok.presentation.notifications.PushKey

interface PushKeyProvider {
    fun start()
    fun stop()
    fun getPushKey(): Map<String, PushKey>
}