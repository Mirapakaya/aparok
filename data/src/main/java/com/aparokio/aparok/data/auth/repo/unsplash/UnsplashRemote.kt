package com.aparokio.aparok.data.auth.repo.unsplash

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.UnsplashImage
import com.aparokio.aparok.core_models.primitives.SpaceId

interface UnsplashRemote {
    fun search(query: String, limit: Int) : List<UnsplashImage>
    fun download(
        space: SpaceId,
        id: Id,
        createdInContext: Id? = null,
        createdInContextRef: String? = null
    ): Hash
}