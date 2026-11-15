package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.ObjectType

@Deprecated("To be deleted")
interface ObjectTypesProvider {
    fun get(): List<ObjectType>
    fun set(objectTypes: List<ObjectType>)
}