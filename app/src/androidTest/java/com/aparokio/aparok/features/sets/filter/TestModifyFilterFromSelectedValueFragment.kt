package com.aparokio.aparok.features.sets.filter

import com.aparokio.aparok.presentation.sets.filter.FilterViewModel
import com.aparokio.aparok.ui.sets.modals.filter.ModifyFilterFromSelectedValueFragment

class TestModifyFilterFromSelectedValueFragment : ModifyFilterFromSelectedValueFragment() {

    init {
        factory = testVmFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testVmFactory: FilterViewModel.Factory
    }
}