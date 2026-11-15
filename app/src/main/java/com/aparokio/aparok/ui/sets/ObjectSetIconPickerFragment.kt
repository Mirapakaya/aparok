package com.aparokio.aparok.ui.sets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_utils.ext.argBoolean
import com.aparokio.aparok.core_utils.ext.setVisible
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.di.feature.DefaultComponentParam
import com.aparokio.aparok.presentation.picker.IconPickerViewModel
import com.aparokio.aparok.presentation.editor.picker.ObjectSetIconPickerViewModelFactory
import com.aparokio.aparok.ui.editor.modals.IconPickerFragmentBase
import javax.inject.Inject

class ObjectSetIconPickerFragment : IconPickerFragmentBase<Id>() {

    @Inject
    lateinit var factory: ObjectSetIconPickerViewModelFactory
    override val vm by viewModels<IconPickerViewModel<Id>> { factory }

    override val target: Id
        get() = context

    private val showRemoveButton: Boolean
        get() = argBoolean(ARG_SHOW_REMOVE_BUTTON)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRemoveIcon.setVisible(showRemoveButton)
    }

    override fun injectDependencies() {
        componentManager()
            .objectSetIconPickerComponent
            .get(
                params = DefaultComponentParam(
                    ctx = context,
                    space = SpaceId(space)
                )
            )
            .inject(this)
    }

    override fun releaseDependencies() {
        componentManager().objectSetIconPickerComponent.release()
    }
}

const val ARG_SHOW_REMOVE_BUTTON = "arg.show_remove_button"