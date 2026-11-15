package com.aparokio.aparok.presentation.sets

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ObjectSetSession {
    val currentViewerId = MutableStateFlow<String?>(null)
}