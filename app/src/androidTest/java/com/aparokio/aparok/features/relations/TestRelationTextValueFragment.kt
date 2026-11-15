package com.aparokio.aparok.features.relations

import com.aparokio.aparok.presentation.sets.RelationTextValueViewModel
import com.aparokio.aparok.ui.relations.RelationTextValueFragment

class TestRelationTextValueFragment : RelationTextValueFragment() {

    init {
        factory = testVmFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testVmFactory: RelationTextValueViewModel.Factory
    }
}