package com.aparokio.aparok.domain.unsplash

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase

class DownloadUnsplashImage(
    private val repo: UnsplashRepository
) : BaseUseCase<Hash, DownloadUnsplashImage.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.download(
            id = params.picture,
            space = params.space,
            createdInContext = params.createdInContext,
            createdInContextRef = params.createdInContextRef
        )
    }

    class Params(
        val picture: Id,
        val space: SpaceId,
        val createdInContext: Id? = null,
        val createdInContextRef: String? = null
    )
}