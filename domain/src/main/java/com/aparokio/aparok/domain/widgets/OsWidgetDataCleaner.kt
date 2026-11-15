package com.aparokio.aparok.domain.widgets

interface OsWidgetDataCleaner {
    suspend fun clearAll()

    object NoOp : OsWidgetDataCleaner {
        override suspend fun clearAll() = Unit
    }
}
