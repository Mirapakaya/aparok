package com.aparokio.aparok.ui.sets.modals.filter

import com.aparokio.aparok.presentation.sets.model.Viewer

interface UpdateConditionActionReceiver {
    fun update(condition: Viewer.Filter.Condition)
}