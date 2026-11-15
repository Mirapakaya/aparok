package com.aparokio.aparok.di.main

import android.content.Context
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.domain.widgets.OsWidgetSpacesSync
import com.aparokio.aparok.feature_os_widgets.ui.OsSpacesListWidgetUpdater

/**
 * Decorator for [OsWidgetSpacesSync] that also triggers a widget refresh
 * after syncing spaces data to DataStore.
 */
class OsWidgetSpacesSyncWithUpdate(
    private val delegate: OsWidgetSpacesSync,
    private val context: Context
) : OsWidgetSpacesSync {

    override suspend fun sync(spaces: List<ObjectWrapper.SpaceView>) {
        // First, sync data to DataStore
        delegate.sync(spaces)
        // Then, trigger widget refresh so it picks up the new data
        OsSpacesListWidgetUpdater.updateAllWidgets(context)
    }
}
