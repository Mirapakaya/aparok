package com.aparokio.aparok.presentation.search

import com.aparokio.aparok.presentation.navigation.DefaultSearchItem

sealed class ObjectSearchSection : DefaultSearchItem {
    data object RecentlyOpened: ObjectSearchSection()
    sealed class SelectWidgetSource : ObjectSearchSection() {
        data object FromMyObjects: SelectWidgetSource()
        data object FromLibrary: SelectWidgetSource()
        data object System: SelectWidgetSource()
        data object Suggested: SelectWidgetSource()
    }
}