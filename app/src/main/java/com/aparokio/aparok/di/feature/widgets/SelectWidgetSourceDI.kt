package com.aparokio.aparok.di.feature.widgets

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.debugging.Logger
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.widgets.GetSuggestedWidgetTypes
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.search.ObjectSearchViewModel
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.widgets.SelectWidgetSourceViewModel
import com.aparokio.aparok.presentation.widgets.WidgetDispatchEvent
import com.aparokio.aparok.ui.widgets.SelectWidgetSourceFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [SelectWidgetSourceModule::class],
    dependencies = [SelectWidgetSourceDependencies::class]
)
@PerModal
interface SelectWidgetSourceComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            vmParams: ObjectSearchViewModel.VmParams,
            dependencies: SelectWidgetSourceDependencies
        ): SelectWidgetSourceComponent
    }

    fun inject(fragment: SelectWidgetSourceFragment)
}

interface SelectWidgetSourceDependencies : ComponentDependencies {
    fun dispatcherWidgets(): Dispatcher<WidgetDispatchEvent>
    fun blockRepository(): BlockRepository
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun analyticsHelper(): AnalyticSpaceHelperDelegate
    fun searchObjects(): SearchObjects
    fun fieldParser(): FieldParser
    fun logger(): Logger
    fun spaceViews(): SpaceViewSubscriptionContainer
}

@Module
object SelectWidgetSourceModule {

    @JvmStatic
    @PerModal
    @Provides
    fun factory(
        vmParams: ObjectSearchViewModel.VmParams,
        urlBuilder: UrlBuilder,
        analytics: Analytics,
        searchObjects: SearchObjects,
        dispatcher: Dispatcher<WidgetDispatchEvent>,
        analyticsHelper: AnalyticSpaceHelperDelegate,
        fieldParser: FieldParser,
        storeOfObjectTypes: StoreOfObjectTypes,
        getSuggestedWidgetTypes: GetSuggestedWidgetTypes,
        spaceViewSubscriptionContainer: SpaceViewSubscriptionContainer
    ): SelectWidgetSourceViewModel.Factory = SelectWidgetSourceViewModel.Factory(
        vmParams = vmParams,
        urlBuilder = urlBuilder,
        searchObjects = searchObjects,
        analytics = analytics,
        dispatcher = dispatcher,
        analyticSpaceHelperDelegate = analyticsHelper,
        fieldParser = fieldParser,
        storeOfObjectTypes = storeOfObjectTypes,
        getSuggestedWidgetTypes = getSuggestedWidgetTypes,
        spaceViews = spaceViewSubscriptionContainer
    )
}