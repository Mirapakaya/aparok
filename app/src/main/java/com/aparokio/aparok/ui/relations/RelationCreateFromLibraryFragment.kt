package com.aparokio.aparok.ui.relations

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.presentation.relations.RelationCreateFromLibraryViewModel
import javax.inject.Inject

@Deprecated("to delete")
class RelationCreateFromLibraryFragment : RelationCreateFromScratchBaseFragment() {

    @Inject
    lateinit var factory: RelationCreateFromLibraryViewModel.Factory
    override val vm: RelationCreateFromLibraryViewModel by viewModels { factory }

    override fun onStart() {
        super.onStart()
        subscribe(vm.navigation, ::onNavigation)
    }

    private fun onNavigation(navigationEvent: RelationCreateFromLibraryViewModel.Navigation) {
        when (navigationEvent) {
            is RelationCreateFromLibraryViewModel.Navigation.Back -> {
            }
            else -> {
                // do nothing
            }
        }
    }

    override fun onCreateRelationClicked() {
        vm.onCreateRelationClicked()
    }

    override fun onLimitObjectTypeClicked() {
        findNavController().navigate(
            R.id.limitObjectTypeScreen,
            LimitObjectTypeFragment.args(
                ctx = ctx,
                space = space,
                flow = LimitObjectTypeFragment.FLOW_LIBRARY
            )
        )
    }

    override fun onConnectWithClicked() {
        findNavController().navigate(
            R.id.relationFormatPickerScreen,
            bundleOf(
                RelationCreateFromScratchFormatPickerFragment.CTX_KEY to ctx,
                RelationCreateFromScratchFormatPickerFragment.FLOW_TYPE to RelationCreateFromScratchFormatPickerFragment.FLOW_LIBRARY,
                RelationCreateFromScratchFormatPickerFragment.SPACE_KEY to space
            )
        )
    }

    override fun injectDependencies() {
        componentManager().relationCreationFromLibraryComponent.get().inject(this)
    }

    override fun releaseDependencies() {
        componentManager().relationCreationFromLibraryComponent.release()
    }

    companion object {
        fun args(query: String, space: Id) = bundleOf(QUERY_KEY to query, SPACE_KEY to space)
    }
}