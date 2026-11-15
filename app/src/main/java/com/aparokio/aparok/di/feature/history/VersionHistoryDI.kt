package com.aparokio.aparok.di.feature.history

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.presentation.history.VersionHistoryVMFactory
import com.aparokio.aparok.presentation.history.VersionHistoryViewModel
import com.aparokio.aparok.presentation.sets.state.DefaultObjectStateReducer
import com.aparokio.aparok.presentation.sets.state.ObjectStateReducer
import com.aparokio.aparok.ui.history.VersionHistoryFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(
    modules = [
        VersionHistoryModule::class,
        VersionHistoryModule.Declarations::class
    ]
)
@PerModal
interface VersionHistoryComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun vmParams(vmParams: VersionHistoryViewModel.VmParams): Builder
        fun build(): VersionHistoryComponent
    }

    fun inject(fragment: VersionHistoryFragment)
}

@Module
object VersionHistoryModule {

    @JvmStatic
    @Provides
    @PerModal
    fun provideObjectStateReducer(): ObjectStateReducer = DefaultObjectStateReducer()

    @Module
    interface Declarations {

        @PerModal
        @Binds
        fun bindViewModelFactory(
            factory: VersionHistoryVMFactory
        ): ViewModelProvider.Factory
    }
}

