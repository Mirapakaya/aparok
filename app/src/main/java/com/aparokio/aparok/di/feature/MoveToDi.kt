package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.moving.MoveToViewModel
import com.aparokio.aparok.presentation.moving.MoveToViewModelFactory
import com.aparokio.aparok.ui.moving.MoveToFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [MoveToModule::class],
    dependencies = [MoveToDependencies::class]
)
@PerScreen
interface MoveToComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            vmParams: MoveToViewModel.VmParams,
            dependencies: MoveToDependencies
        ): MoveToComponent
    }

    fun inject(fragment: MoveToFragment)
}

interface MoveToDependencies : ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun searchObjects(): SearchObjects
    fun analytics(): Analytics
    fun analyticSpaceHelperDelegate(): AnalyticSpaceHelperDelegate
    fun  fieldParser(): FieldParser
    fun storeOfObjectTypes(): StoreOfObjectTypes
}

@Module
object MoveToModule {

    @JvmStatic
    @PerScreen
    @Provides
    fun provideMoveToViewModelFactory(
        vmParams: MoveToViewModel.VmParams,
        urlBuilder: UrlBuilder,
        searchObjects: SearchObjects,
        analytics: Analytics,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        fieldParser: FieldParser,
        storeOfObjectTypes: StoreOfObjectTypes
    ): MoveToViewModelFactory = MoveToViewModelFactory(
        vmParams = vmParams,
        urlBuilder = urlBuilder,
        searchObjects = searchObjects,
        analytics = analytics,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        fieldParser = fieldParser,
        storeOfObjectTypes = storeOfObjectTypes
    )
}