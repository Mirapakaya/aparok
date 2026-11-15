package com.aparokio.aparok.ui.sets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.di.feature.DefaultComponentParam
import com.aparokio.aparok.presentation.objects.menu.ObjectSetMenuViewModel
import com.aparokio.aparok.ui.editor.sheets.ObjectMenuBaseFragment
import javax.inject.Inject

class ObjectSetMenuFragment : ObjectMenuBaseFragment() {

    @Inject
    lateinit var factory: ObjectSetMenuViewModel.Factory
    override val vm by viewModels<ObjectSetMenuViewModel> { factory }

    override fun onStart() {
        super.onStart()
        with(lifecycleScope) {
            subscribe(vm.isObjectArchived) { isArchived ->
                if (isArchived) {
                    findNavController().popBackStack(R.id.objectSetScreen, true)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.publishToWeb.visibility = View.GONE
        binding.publishToWebDivider.visibility = View.GONE
    }

    override fun injectDependencies() {
        componentManager()
            .objectSetMenuComponent
            .get(
                params = DefaultComponentParam(
                    ctx = ctx,
                    space = SpaceId(space)
                )
            )
            .inject(this)
    }

    override fun releaseDependencies() {
        componentManager().objectSetMenuComponent.release()
    }
}