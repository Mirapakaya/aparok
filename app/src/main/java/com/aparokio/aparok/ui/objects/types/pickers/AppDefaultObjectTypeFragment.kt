package com.aparokio.aparok.ui.objects.types.pickers

import android.os.Bundle
import androidx.core.os.bundleOf
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_utils.ext.withParent
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.presentation.objects.ObjectTypeChangeViewModel
import com.aparokio.aparok.ui.objects.BaseObjectTypeChangeFragment

/**
 * Selecting a default object type for application
 */
class AppDefaultObjectTypeFragment : BaseObjectTypeChangeFragment() {

    override fun onItemClicked(item: ObjectWrapper.Type) {
        withParent<ObjectTypeSelectionListener> {
            onSelectObjectType(objType = item)
        }
    }

    override fun resolveTitle(): String = getString(R.string.default_type_screen_title)

    override fun injectDependencies() {
        val params = ObjectTypeChangeViewModel.VmParams(
            spaceId = SpaceId(space),
            screen = ObjectTypeChangeViewModel.Screen.DEFAULT_OBJECT_TYPE
        )
        componentManager().objectTypeChangeComponent.get(params).inject(this)
    }

    override fun releaseDependencies() {
        componentManager().objectTypeChangeComponent.release()
    }

    companion object {
        fun args(space: Id) : Bundle = bundleOf(
            ARG_SPACE to space
        )
    }
}
