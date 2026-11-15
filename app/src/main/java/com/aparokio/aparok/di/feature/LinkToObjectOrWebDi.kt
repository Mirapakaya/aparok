package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.core_utils.tools.UrlValidator
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.editor.Editor
import com.aparokio.aparok.presentation.linking.LinkToObjectOrWebViewModel
import com.aparokio.aparok.presentation.linking.LinkToObjectOrWebViewModelFactory
import com.aparokio.aparok.ui.linking.LinkToObjectOrWebPagesFragment
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(
    modules = [LinkToObjectOrWebModule::class]
)
@PerModal
interface LinkToObjectOrWebSubComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun withParams(params: LinkToObjectOrWebViewModel.VmParams): Builder
        fun module(module: LinkToObjectOrWebModule): Builder
        fun build(): LinkToObjectOrWebSubComponent
    }

    fun inject(fragment: LinkToObjectOrWebPagesFragment)
}

@Module
object LinkToObjectOrWebModule {

    @JvmStatic
    @PerModal
    @Provides
    fun provideLinkToObjectViewModelFactory(
        vmParams: LinkToObjectOrWebViewModel.VmParams,
        urlBuilder: UrlBuilder,
        storeOfObjectTypes: StoreOfObjectTypes,
        searchObjects: SearchObjects,
        analytics: Analytics,
        stores: Editor.Storage,
        urlValidator: UrlValidator,
        analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
        fieldParser: FieldParser,
        spaceViews: SpaceViewSubscriptionContainer
    ): LinkToObjectOrWebViewModelFactory = LinkToObjectOrWebViewModelFactory(
        vmParams = vmParams,
        urlBuilder = urlBuilder,
        storeOfObjectTypes = storeOfObjectTypes,
        searchObjects = searchObjects,
        analytics = analytics,
        stores = stores,
        urlValidator = urlValidator,
        analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
        fieldParser = fieldParser,
        spaceViews = spaceViews
    )
}