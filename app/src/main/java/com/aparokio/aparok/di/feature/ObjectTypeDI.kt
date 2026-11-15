package com.aparokio.aparok.di.feature

import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.presentation.objects.ObjectTypeChangeViewModel
import com.aparokio.aparok.ui.objects.types.pickers.AppDefaultObjectTypeFragment
import com.aparokio.aparok.ui.objects.types.pickers.CollectionAddObjectTypeFragment
import com.aparokio.aparok.ui.objects.types.pickers.DataViewSelectSourceFragment
import com.aparokio.aparok.ui.objects.types.pickers.EditorObjectTypeUpdateFragment
import com.aparokio.aparok.ui.objects.types.pickers.EmptyDataViewSelectSourceFragment
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [ObjectTypeChangeModule::class])
@PerScreen
interface ObjectTypeChangeSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: ObjectTypeChangeViewModel.VmParams
        ): ObjectTypeChangeSubComponent
    }

    fun inject(fragment: DataViewSelectSourceFragment)
    fun inject(fragment: EmptyDataViewSelectSourceFragment)
    fun inject(fragment: AppDefaultObjectTypeFragment)
    fun inject(fragment: CollectionAddObjectTypeFragment)
    fun inject(fragment: EditorObjectTypeUpdateFragment)
}

@Module
object ObjectTypeChangeModule {

}