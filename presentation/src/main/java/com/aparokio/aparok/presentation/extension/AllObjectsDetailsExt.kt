package com.aparokio.aparok.presentation.extension

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectViewDetails
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.ext.mapToObjectWrapperType
import com.aparokio.aparok.core_models.ext.mapToOptionObject
import com.aparokio.aparok.core_models.ext.toBookmarkObject
import com.aparokio.aparok.core_models.ext.toDateObject
import com.aparokio.aparok.core_models.ext.toFileObject
import com.aparokio.aparok.core_models.ext.toInternalFlagsObject
import com.aparokio.aparok.core_models.ext.toObject
import com.aparokio.aparok.presentation.objects.getProperType

fun ObjectViewDetails.getStruct(id: Id): Struct? = details[id]

fun ObjectViewDetails.getObject(id: Id): ObjectWrapper.Basic? {
    return details[id]?.toObject()
}

fun ObjectViewDetails.getOptionObject(id: Id): ObjectWrapper.Option? {
    return details[id]?.mapToOptionObject()
}

fun ObjectViewDetails.getFileObject(id: Id): ObjectWrapper.File? {
    return details[id]?.toFileObject()
}

fun ObjectViewDetails.getTypeObject(id: Id): ObjectWrapper.Type? {
    return details[id]?.mapToObjectWrapperType()
}

fun ObjectViewDetails.getDateObject(id: Id): ObjectWrapper.Date? {
    return details[id]?.toDateObject()
}

fun ObjectViewDetails.getBookmarkObject(id: Id): ObjectWrapper.Bookmark? {
    return details[id]?.toBookmarkObject()
}

fun ObjectViewDetails.getInternalFlagsObject(id: Id): ObjectWrapper.ObjectInternalFlags? {
    return details[id]?.toInternalFlagsObject()
}

fun ObjectViewDetails.getTypeForObject(currentObjectId: Id): ObjectWrapper.Type? {
    val currentObject = getObject(currentObjectId)
    val type = currentObject?.getProperType()
    if (type != null) {
        val objType = getTypeObject(type)
        if (objType != null) {
            return objType
        }
    }
    return null
}