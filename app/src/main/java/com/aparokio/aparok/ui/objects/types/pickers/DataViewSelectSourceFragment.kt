package com.aparokio.aparok.ui.objects.types.pickers

import androidx.core.os.bundleOf
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_utils.ext.withParent
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.presentation.objects.ObjectTypeChangeViewModel
import com.aparokio.aparok.ui.objects.BaseObjectTypeChangeFragment


class DataViewSelectSourceFragment : BaseObjectTypeChangeFragment() {

    override fun onItemClicked(item: ObjectWrapper.Type) {
        withParent<OnDataViewSelectSourceAction> {
            onProceedWithSelectSource(id = item.id)
        }
    }

    override fun resolveTitle(): String = getString(R.string.select_query)

    override fun injectDependencies() {
        val params = ObjectTypeChangeViewModel.VmParams(
            spaceId = SpaceId(space),
            screen = ObjectTypeChangeViewModel.Screen.DATA_VIEW_SOURCE,
            selectedTypes = selectedTypes
        )
        componentManager().objectTypeChangeComponent.get(params).inject(this)
    }

    override fun releaseDependencies() {
        componentManager().objectTypeChangeComponent.release()
    }

    companion object {
        fun newInstance(space: Id, selectedTypes: List<Id>) = DataViewSelectSourceFragment().apply {
            arguments = bundleOf(
                ARG_SPACE to space,
                ARG_SELECTED_TYPES to selectedTypes
            )
        }
    }
}
