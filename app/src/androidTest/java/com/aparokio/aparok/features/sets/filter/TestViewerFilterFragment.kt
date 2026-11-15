package com.aparokio.aparok.features.sets.filter

import com.aparokio.aparok.presentation.sets.filter.ViewerFilterViewModel
import com.aparokio.aparok.ui.sets.ViewerFilterFragment

class TestViewerFilterFragment : ViewerFilterFragment() {

    init {
        factory = testVmFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testVmFactory: ViewerFilterViewModel.Factory
    }
}