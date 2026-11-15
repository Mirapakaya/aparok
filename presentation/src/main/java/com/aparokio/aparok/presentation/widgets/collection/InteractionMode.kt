package com.aparokio.aparok.presentation.widgets.collection

sealed class InteractionMode {
    object Edit : InteractionMode()
    object View : InteractionMode()
}