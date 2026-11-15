package com.aparokio.aparok.features.relations

import com.aparokio.aparok.presentation.sets.RelationDateValueViewModel
import com.aparokio.aparok.ui.relations.RelationDateValueFragment

class TestRelationDateValueFragment: RelationDateValueFragment() {

    init {
        factory = testVmFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testVmFactory: RelationDateValueViewModel.Factory
    }
}