package com.aparokio.aparok.domain.cover

import com.aparokio.aparok.core_models.Id


interface GradientCollectionProvider {
    fun provide(): List<Id>
}