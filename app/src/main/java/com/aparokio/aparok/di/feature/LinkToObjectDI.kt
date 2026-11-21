package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.block.interactor.sets.GetObjectTypes
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.linking.LinkToObjectViewModelFactory
import com.aparokio.aparok.presentation.search.ObjectSearchViewModel
import com.aparokio.aparok.ui.linking.LinkToObjectFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [LinkToObjectModule::class],
    dependencies = [LinkToObjectDependencies::class]
)
@PerScreen
interface LinkToObjectComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            params: ObjectSearchViewModel.VmParams,
            dependencies: LinkToObjectDependencies
        ) : LinkToObjectComponent
    }

    fun inject(fragment: LinkToObjectFragment)
}

interface LinkToObjectDependencies: ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun getObjectTypes(): GetObjectTypes
    fun searchObjects(): SearchObjects
    fun analytics(): Analytics
    fun analyticSpaceHelperDelegate(): AnalyticSpaceHelperDelegate
    fun dateProvider(): DateProvider
    fun fieldParser(): FieldParser
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun spaceViews(): SpaceViewSubscriptionContainer
}

@Module
object LinkToObjectModule {

    @JvmStatic
    @PerScreen
    @Provides
    fun provideLinkToObjectViewModelFactory(
        vmParams: ObjectSearchViewModel.VmParams,
        urlBuilder: UrlBuilder,
        searchObjects: SearchObjects,
        analytics: Analytics,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        fieldParser: FieldParser,
        storeOfObjectTypes: StoreOfObjectTypes,
        spaceViews: SpaceViewSubscriptionContainer
    ): LinkToObjectViewModelFactory = LinkToObjectViewModelFactory(
        vmParams = vmParams,
        urlBuilder = urlBuilder,
        searchObjects = searchObjects,
        analytics = analytics,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        fieldParser = fieldParser,
        storeOfObjectTypes = storeOfObjectTypes,
        spaceViews = spaceViews
    )
}
