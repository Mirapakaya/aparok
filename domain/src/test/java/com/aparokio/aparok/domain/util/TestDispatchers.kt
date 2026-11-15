package com.aparokio.aparok.domain.util

import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
val dispatchers = AppCoroutineDispatchers(
    io = StandardTestDispatcher(),
    main = StandardTestDispatcher(),
    computation = StandardTestDispatcher()
)