package com.aparokio.aparok.di.main

import android.content.Context
import com.aparokio.aparok.domain.widgets.OsWidgetDataViewSync
import com.aparokio.aparok.feature_os_widgets.ui.OsDataViewWidgetUpdater

/**
 * Decorator for [OsWidgetDataViewSync] that also triggers a widget refresh
 * after syncing items to DataStore.
 */
class OsWidgetDataViewSyncWithUpdate(
    private val delegate: OsWidgetDataViewSync,
    private val context: Context
) : OsWidgetDataViewSync {

    override suspend fun sync() {
        delegate.sync()
        OsDataViewWidgetUpdater.updateAllWidgets(context)
    }
}
