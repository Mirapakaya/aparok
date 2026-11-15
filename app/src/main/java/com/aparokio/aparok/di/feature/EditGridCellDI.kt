package com.aparokio.aparok.di.feature;

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.domain.`object`.ReloadObject
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider.Companion.DATA_VIEW_PROVIDER_TYPE
import com.aparokio.aparok.presentation.relations.providers.ObjectRelationProvider.Companion.INTRINSIC_PROVIDER_TYPE
import com.aparokio.aparok.presentation.relations.providers.ObjectValueProvider
import com.aparokio.aparok.presentation.sets.RelationDateValueViewModel
import com.aparokio.aparok.presentation.sets.RelationTextValueViewModel
import com.aparokio.aparok.ui.relations.RelationDateValueFragment
import com.aparokio.aparok.ui.relations.RelationTextValueFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [RelationTextValueModule::class])
@PerModal
interface RelationTextValueSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationTextValueModule): Builder
        fun build(): RelationTextValueSubComponent
    }

    fun inject(fragment: RelationTextValueFragment)
}

@Module
object RelationTextValueModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideRelationTextValueViewModelFactory(
        @Named(INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        reloadObject: ReloadObject,
        analytics: Analytics,
        storeOfObjectTypes: StoreOfObjectTypes,
        storeOfRelations: StoreOfRelations
    ) = RelationTextValueViewModel.Factory(
        values = values,
        reloadObject = reloadObject,
        analytics = analytics,
        storeOfObjectTypes = storeOfObjectTypes,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideReloadObjectUseCase(
        repo: BlockRepository
    ): ReloadObject = ReloadObject(repo)
}

@Subcomponent(modules = [RelationDataViewTextValueModule::class])
@PerModal
interface RelationDataViewTextValueSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationDataViewTextValueModule): Builder
        fun build(): RelationDataViewTextValueSubComponent
    }

    fun inject(fragment: RelationTextValueFragment)
}

@Module
object RelationDataViewTextValueModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideRelationTextValueViewModelFactory(
        @Named(DATA_VIEW_PROVIDER_TYPE) values: ObjectValueProvider,
        reloadObject: ReloadObject,
        analytics: Analytics,
        storeOfObjectTypes: StoreOfObjectTypes,
        storeOfRelations: StoreOfRelations
    ) = RelationTextValueViewModel.Factory(
        values = values,
        reloadObject = reloadObject,
        analytics = analytics,
        storeOfObjectTypes = storeOfObjectTypes,
        storeOfRelations = storeOfRelations
    )

    @JvmStatic
    @Provides
    @PerModal
    fun provideReloadObjectUseCase(
        repo: BlockRepository
    ): ReloadObject = ReloadObject(repo)
}

@Subcomponent(modules = [RelationDateValueModule::class])
@PerModal
interface DefaultRelationDataValueSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationDateValueModule): Builder
        fun build(): DefaultRelationDataValueSubComponent
    }

    fun inject(fragment: RelationDateValueFragment)
}

@Module
object RelationDateValueModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideEditGridCellViewModelFactory(
        @Named(INTRINSIC_PROVIDER_TYPE) values: ObjectValueProvider,
        dateProvider: DateProvider,
        storeOfRelations: StoreOfRelations
    ) = RelationDateValueViewModel.Factory(values, dateProvider, storeOfRelations)
}

@Subcomponent(modules = [RelationDataViewDateValueModule::class])
@PerModal
interface DataViewRelationDataValueSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: RelationDataViewDateValueModule): Builder
        fun build(): DataViewRelationDataValueSubComponent
    }

    fun inject(fragment: RelationDateValueFragment)
}

@Module
object RelationDataViewDateValueModule {
    @JvmStatic
    @Provides
    @PerModal
    fun provideEditGridCellViewModelFactory(
        @Named(DATA_VIEW_PROVIDER_TYPE) values: ObjectValueProvider,
        storeOfRelations: StoreOfRelations,
        dateProvider: DateProvider
    ) = RelationDateValueViewModel.Factory(values, dateProvider, storeOfRelations)
}
