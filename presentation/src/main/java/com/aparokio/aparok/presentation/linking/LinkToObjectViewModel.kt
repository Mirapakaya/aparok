package com.aparokio.aparok.presentation.linking

import androidx.lifecycle.viewModelScope
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.SupportedLayouts
import com.aparokio.aparok.domain.base.Resultat
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.navigation.DefaultObjectView
import com.aparokio.aparok.presentation.search.ObjectSearchConstants
import com.aparokio.aparok.presentation.search.ObjectSearchViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LinkToObjectViewModel(
    private val vmParams: VmParams,
    urlBuilder: UrlBuilder,
    searchObjects: SearchObjects,
    analytics: Analytics,
    analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    fieldParser: FieldParser,
    storeOfObjectTypes: StoreOfObjectTypes,
    private val spaceViews: SpaceViewSubscriptionContainer
) : ObjectSearchViewModel(
    vmParams = vmParams,
    urlBuilder = urlBuilder,
    searchObjects = searchObjects,
    analytics = analytics,
    analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
    fieldParser = fieldParser,
    storeOfObjectTypes = storeOfObjectTypes,
    spaceViews = spaceViews
) {

    val commands = MutableSharedFlow<Command>(replay = 0)

    init {
        Timber.d("LinkToObjectViewModel init")
    }

    override suspend fun getSearchObjectsParams(ignore: Id?) = SearchObjects.Params(
        space = vmParams.space,
        limit = SEARCH_LIMIT,
        filters = buildList {
            val isOneToOneSpace = spaceViews.get(vmParams.space)?.isOneToOneSpace == true
            addAll(
                ObjectSearchConstants.getFilterLinkTo(
                    ignore = ignore,
                    isOneToOneSpace = isOneToOneSpace
                )
            )
        },
        sorts = ObjectSearchConstants.sortLinkTo,
        fulltext = EMPTY_QUERY,
        keys = ObjectSearchConstants.defaultKeys
    )

    override fun onObjectClicked(view: DefaultObjectView) {
        sendSearchResultEvent(view.id)
        viewModelScope.launch {
            commands.emit(
                Command.Link(
                    link = view.id,
                    isBookmark = view.layout == ObjectType.Layout.BOOKMARK
                )
            )
        }
    }

    override fun onDialogCancelled() {
        viewModelScope.launch {
            commands.emit(Command.Exit)
        }
    }

    override suspend fun setObjects(data: List<ObjectWrapper.Basic>) {
        Timber.d("LinkToObjectViewModel setObjects called with ${data.size} items")
        val isOneToOneSpace = spaceViews.get(vmParams.space)?.isOneToOneSpace == true
        val supportedLayouts = SupportedLayouts.getLayouts(isOneToOneSpace)
        objects.emit(
            Resultat.success(data.filter {
                supportedLayouts.contains(it.layout)
            })
        )
    }

    sealed class Command {
        data object Exit : Command()
        data class Link(
            val link: Id,
            val isBookmark: Boolean
        ) : Command()
    }
}