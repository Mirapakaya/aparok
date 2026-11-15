package com.aparokio.aparok.middleware

import aparok.Rpc.Unsplash.Download
import aparok.Rpc.Unsplash.Search
import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.UnsplashImage
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.data.auth.repo.unsplash.UnsplashRemote
import com.aparokio.aparok.middleware.interactor.MiddlewareProtobufLogger
import com.aparokio.aparok.middleware.mappers.core
import com.aparokio.aparok.middleware.service.MiddlewareService
import javax.inject.Inject

class UnsplashMiddleware @Inject constructor(
    private val service: MiddlewareService,
    private val logger: MiddlewareProtobufLogger
) : UnsplashRemote {

    override fun search(query: String, limit: Int): List<UnsplashImage> {
        val request = Search.Request(
            query = query,
            limit = limit
        ).also { logger.logRequest(it) }
        val response = service.unsplashSearch(request = request).also { logger.logResponse(it) }
        return response.pictures.map { p -> p.core() }
    }

    override fun download(
        space: SpaceId,
        id: Id,
        createdInContext: Id?,
        createdInContextRef: String?
    ): Hash {
        val request = Download.Request(
            pictureId = id,
            spaceId = space.id,
            createdInContext = createdInContext.orEmpty(),
            createdInContextRef = createdInContextRef.orEmpty()
        ).also {
            logger.logRequest(it)
        }
        val response = service.unsplashDownload(request = request).also { logger.logResponse(it) }
        return response.objectId
    }
}