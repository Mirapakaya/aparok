package com.aparokio.aparok.feature_os_widgets.persistence

import com.aparokio.aparok.core_models.DVFilter
import com.aparokio.aparok.core_models.DVFilterCondition
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectTypeUniqueKeys
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.ext.mapToObjectWrapperType
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.presentation.search.ObjectSearchConstants
import timber.log.Timber

/**
 * Shared one-shot fetcher for object types in a space.
 * Used by OS widget config/view sync flows to resolve type names/icons.
 */
suspend fun fetchObjectTypesForSpace(
    searchObjects: SearchObjects,
    spaceId: SpaceId
): Map<Id, ObjectWrapper.Type> {
    val filters = buildList {
        add(
            DVFilter(
                relation = Relations.IS_DELETED,
                condition = DVFilterCondition.NOT_EQUAL,
                value = true
            )
        )
        add(
            DVFilter(
                relation = Relations.IS_ARCHIVED,
                condition = DVFilterCondition.NOT_EQUAL,
                value = true
            )
        )
        add(
            DVFilter(
                relation = Relations.TYPE_UNIQUE_KEY,
                condition = DVFilterCondition.NOT_EQUAL,
                value = ObjectTypeUniqueKeys.TEMPLATE
            )
        )
        add(
            DVFilter(
                relation = Relations.LAYOUT,
                condition = DVFilterCondition.EQUAL,
                value = ObjectType.Layout.OBJECT_TYPE.code.toDouble()
            )
        )
        add(
            DVFilter(
                relation = Relations.UNIQUE_KEY,
                condition = DVFilterCondition.NOT_EMPTY
            )
        )
    }

    val params = SearchObjects.Params(
        space = spaceId,
        filters = filters,
        sorts = emptyList(),
        keys = ObjectSearchConstants.defaultKeysObjectType,
        limit = 0
    )

    return try {
        val results = searchObjects(params).getOrNull() ?: emptyList()
        results.mapNotNull { obj ->
            obj.map.mapToObjectWrapperType()?.let { type ->
                type.id to type
            }
        }.toMap()
    } catch (e: Exception) {
        Timber.e(e, "Error fetching object types for space")
        emptyMap()
    }
}
