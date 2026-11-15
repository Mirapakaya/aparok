package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.domain.`object`.SetObjectDetails
import com.aparokio.aparok.presentation.sets.ObjectSetRecordViewModel
import com.aparokio.aparok.ui.sets.modals.SetObjectSetRecordNameFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [ObjectSetRecordModule::class])
@ObjectSetRecordScope
interface ObjectSetRecordSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun module(module: ObjectSetRecordModule): Builder
        fun build(): ObjectSetRecordSubComponent
    }

    fun inject(fragment: SetObjectSetRecordNameFragment)
}

@Module
object ObjectSetRecordModule {
    @JvmStatic
    @Provides
    @ObjectSetRecordScope
    fun provideObjectSetRecordViewModelFactory(
        setObjectDetails: SetObjectDetails
    ): ObjectSetRecordViewModel.Factory = ObjectSetRecordViewModel.Factory(
        setObjectDetails = setObjectDetails,
    )
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ObjectSetRecordScope