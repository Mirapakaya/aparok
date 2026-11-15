package com.aparokio.aparok.domain.unsplash

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.UnsplashImage
import com.aparokio.aparok.core_models.primitives.SpaceId

interface UnsplashRepository {
    fun search(query: String, limit: Int) : List<UnsplashImage>
    fun download(
        id: Id,
        space: SpaceId,
        createdInContext: Id? = null,
        createdInContextRef: String? = null
    ): Hash
}