package com.aparokio.aparok.domain.cover

import com.aparokio.aparok.domain.base.BaseUseCase

class GetCoverImageCollection(private val provider: CoverCollectionProvider) : BaseUseCase<List<CoverImage>, Unit>() {
    override suspend fun run(params: Unit) = safe {
        provider.provide()
    }
}