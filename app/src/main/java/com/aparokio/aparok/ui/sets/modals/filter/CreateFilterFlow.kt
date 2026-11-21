package com.aparokio.aparok.ui.sets.modals.filter

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.presentation.sets.model.SimpleRelationView

interface CreateFilterFlow {
    fun onRelationSelected(ctx: Id, relation: SimpleRelationView)
    fun onFilterCreated()
}