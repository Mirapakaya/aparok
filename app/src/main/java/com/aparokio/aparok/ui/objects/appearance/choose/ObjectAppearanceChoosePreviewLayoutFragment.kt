package com.aparokio.aparok.ui.objects.appearance.choose

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.di.feature.DefaultComponentParam
import com.aparokio.aparok.presentation.objects.appearance.choose.ObjectAppearanceChoosePreviewLayoutViewModel
import com.aparokio.aparok.presentation.objects.appearance.choose.ObjectAppearanceChooseSettingsView
import javax.inject.Inject

class ObjectAppearanceChoosePreviewLayoutFragment :
    ObjectAppearanceChooseFragmentBase<ObjectAppearanceChooseSettingsView.PreviewLayout, ObjectAppearanceChoosePreviewLayoutViewModel>() {

    @Inject
    lateinit var factory: ObjectAppearanceChoosePreviewLayoutViewModel.Factory
    override val vm by viewModels<ObjectAppearanceChoosePreviewLayoutViewModel> { factory }
    override val title: Int = R.string.preview_layout

    override fun injectDependencies() {
        componentManager()
            .objectAppearancePreviewLayoutComponent
            .get(
                params = DefaultComponentParam(
                    ctx = ctx, space = SpaceId(space)
                )
            )
            .inject(this)
    }

    override fun releaseDependencies() {
        componentManager().objectAppearancePreviewLayoutComponent.release()
    }

    companion object {
        fun new(ctx: Id, space: Id, block: Id) = ObjectAppearanceChoosePreviewLayoutFragment().apply {
            arguments = bundleOf(
                CONTEXT_ID_KEY to ctx,
                SPACE_KEY to space,
                BLOCK_ID_KEY to block
            )
        }
    }
}