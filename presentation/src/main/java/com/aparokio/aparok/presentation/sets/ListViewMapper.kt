package com.aparokio.aparok.presentation.sets

import com.aparokio.aparok.core_models.DVViewer
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.ObjectStore
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.getTypeOfObject
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.core_models.ui.objectIcon
import com.aparokio.aparok.presentation.objects.relationsFilteredByHiddenAndDescription
import com.aparokio.aparok.presentation.objects.setTypeRelationIconsAsNone
import com.aparokio.aparok.presentation.sets.model.Viewer

suspend fun DVViewer.buildListViews(
    objects: List<Id>,
    relations: List<ObjectWrapper.Relation>,
    urlBuilder: UrlBuilder,
    store: ObjectStore,
    objectOrderIds: List<Id>,
    fieldParser: FieldParser,
    storeOfObjectTypes: StoreOfObjectTypes
): List<Viewer.ListView.Item> {
    val items = objects.mapNotNull { id ->
        val obj = store.get(id)
        if (obj != null && obj.isValid) {
            when (obj.layout) {
                ObjectType.Layout.PROFILE -> {
                    val description = if (relations.any { it.key == Relations.DESCRIPTION }) {
                        obj.description
                    } else {
                        null
                    }
                    Viewer.ListView.Item.Profile(
                        objectId = obj.id,
                        relations = obj.relationsFilteredByHiddenAndDescription(
                            relations = relations,
                            urlBuilder = urlBuilder,
                            settings = viewerRelations,
                            storeOfObjects = store,
                            fieldParser = fieldParser,
                            storeOfObjectTypes = storeOfObjectTypes
                        ).setTypeRelationIconsAsNone(),
                        name = fieldParser.getObjectName(obj),
                        icon = obj.objectIcon(
                            builder = urlBuilder,
                            objType = storeOfObjectTypes.getTypeOfObject(obj)
                        ),
                        description = description,
                        hideIcon = hideIcon
                    )
                }
                ObjectType.Layout.TODO -> {
                    val description = if (relations.any { it.key == Relations.DESCRIPTION }) {
                        obj.description
                    } else {
                        null
                    }
                    Viewer.ListView.Item.Task(
                        objectId = obj.id,
                        relations = obj.relationsFilteredByHiddenAndDescription(
                            relations = relations,
                            urlBuilder = urlBuilder,
                            settings = viewerRelations,
                            storeOfObjects = store,
                            fieldParser = fieldParser,
                            storeOfObjectTypes = storeOfObjectTypes
                        ).setTypeRelationIconsAsNone(),
                        name = fieldParser.getObjectName(obj),
                        done = obj.done == true,
                        description = description
                    )
                }
                else -> {
                    val description = if (relations.any { it.key == Relations.DESCRIPTION }) {
                        obj.description
                    } else {
                        null
                    }
                    Viewer.ListView.Item.Default(
                        objectId = obj.id,
                        relations = obj.relationsFilteredByHiddenAndDescription(
                            relations = relations,
                            urlBuilder = urlBuilder,
                            settings = viewerRelations,
                            storeOfObjects = store,
                            fieldParser = fieldParser,
                            storeOfObjectTypes = storeOfObjectTypes
                        ).setTypeRelationIconsAsNone(),
                        name = fieldParser.getObjectName(obj),
                        icon = obj.objectIcon(
                            builder = urlBuilder,
                            objType = storeOfObjectTypes.getTypeOfObject(obj)
                        ),
                        description = description,
                        hideIcon = hideIcon
                    )
                }
            }
        } else {
            null
        }
    }
    return if (objectOrderIds.isNotEmpty()) {
        items.sortObjects(objectOrderIds)
    } else {
        items
    }
}

private fun List<Viewer.ListView.Item>.sortObjects(objectOrderIds: List<Id>): List<Viewer.ListView.Item> {
    val orderMap = objectOrderIds.mapIndexed { index, id -> id to index }.toMap()
    return sortedBy { orderMap[it.objectId] }
}