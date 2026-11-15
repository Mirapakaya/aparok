package com.aparokio.aparok.core_ui.features.editor

interface ItemProviderAdapter<T> {
    fun provide(pos: Int): T
}
