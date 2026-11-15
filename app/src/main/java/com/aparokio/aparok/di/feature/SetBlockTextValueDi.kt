package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerDialog
import com.aparokio.aparok.domain.block.interactor.UpdateText
import com.aparokio.aparok.domain.clipboard.Copy
import com.aparokio.aparok.domain.clipboard.Paste
import com.aparokio.aparok.presentation.editor.Editor
import com.aparokio.aparok.presentation.objects.block.SetBlockTextValueViewModel
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.editor.modals.SetBlockTextValueFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SetBlockTextValueModule::class])
@PerDialog
interface SetBlockTextValueSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(model: SetBlockTextValueModule): Builder
        fun build(): SetBlockTextValueSubComponent
    }

    fun inject(fragment: SetBlockTextValueFragment)
}

@Module
object SetBlockTextValueModule {

    @JvmStatic
    @Provides
    @PerDialog
    fun provideViewModelFactory(
        storage: Editor.Storage,
        dispatcher: Dispatcher<Payload>,
        paste: Paste,
        copy: Copy,
        updateText: UpdateText,
        analytics: Analytics
    ): SetBlockTextValueViewModel.Factory =
        SetBlockTextValueViewModel.Factory(
            storage = storage,
            dispatcher = dispatcher,
            paste = paste,
            copy = copy,
            updateText = updateText,
            analytics = analytics
        )
}