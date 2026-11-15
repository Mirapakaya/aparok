package com.aparokio.aparok.data.auth.repo.unsplash

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.UnsplashImage
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.unsplash.UnsplashRepository

class UnsplashDataRepository(
    private val remote: UnsplashRemote
) : UnsplashRepository {
    override fun search(
        query: String,
        limit: Int
    ): List<UnsplashImage> = remote.search(
        query = query,
        limit = limit
    )
    override fun download(
        id: Id,
        space: SpaceId,
        createdInContext: Id?,
        createdInContextRef: String?
    ): Hash = remote.download(
        id = id,
        space = space,
        createdInContext = createdInContext,
        createdInContextRef = createdInContextRef
    )
}