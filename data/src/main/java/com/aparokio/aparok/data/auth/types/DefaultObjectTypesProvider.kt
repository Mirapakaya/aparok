package com.aparokio.aparok.data.auth.types

import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.domain.`object`.ObjectTypesProvider

class DefaultObjectTypesProvider : ObjectTypesProvider {

    private val objectTypes: MutableList<ObjectType> = mutableListOf()

    override fun get(): List<ObjectType> = objectTypes

    override fun set(objectTypes: List<ObjectType>) {
        this.objectTypes.apply {
            clear()
            addAll(objectTypes)
        }
    }
}