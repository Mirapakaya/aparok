package com.aparokio.aparok.di.feature.widgets

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.widgets.UpdateWidget
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.presentation.widgets.SelectWidgetTypeViewModel
import com.aparokio.aparok.presentation.widgets.WidgetDispatchEvent
import com.aparokio.aparok.ui.widgets.SelectWidgetTypeFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [SelectWidgetTypeModule::class],
    dependencies = [SelectWidgetTypeDependencies::class]
)
@PerModal
interface SelectWidgetTypeComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: SelectWidgetTypeDependencies): SelectWidgetTypeComponent
    }

    fun inject(fragment: SelectWidgetTypeFragment)
}

interface SelectWidgetTypeDependencies : ComponentDependencies {
    fun dispatcherWidgets(): Dispatcher<WidgetDispatchEvent>
    fun dispatcherPayload(): Dispatcher<Payload>
    fun blockRepository(): BlockRepository
    fun analytics(): Analytics
    fun urlBuilder(): UrlBuilder
    fun dispatchers(): AppCoroutineDispatchers
    fun storeOfObjectTypes(): StoreOfObjectTypes
}

@Module
object SelectWidgetTypeModule {

    @JvmStatic
    @PerModal
    @Provides
    fun factory(
        payloadDispatcher: Dispatcher<Payload>,
        widgetDispatcher: Dispatcher<WidgetDispatchEvent>,
        updateWidget: UpdateWidget,
        appCoroutineDispatchers: AppCoroutineDispatchers,
        analytics: Analytics
    ): SelectWidgetTypeViewModel.Factory = SelectWidgetTypeViewModel.Factory(
        payloadDispatcher = payloadDispatcher,
        widgetDispatcher = widgetDispatcher,
        updateWidget = updateWidget,
        appCoroutineDispatchers = appCoroutineDispatchers,
        analytics = analytics
    )
}