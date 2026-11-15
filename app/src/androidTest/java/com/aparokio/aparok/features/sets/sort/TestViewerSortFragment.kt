package com.aparokio.aparok.features.sets.sort

import com.aparokio.aparok.presentation.sets.sort.ViewerSortViewModel
import com.aparokio.aparok.ui.sets.modals.sort.ViewerSortFragment

class TestViewerSortFragment : ViewerSortFragment() {

    init {
        factory = testVmFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testVmFactory: ViewerSortViewModel.Factory
    }
}