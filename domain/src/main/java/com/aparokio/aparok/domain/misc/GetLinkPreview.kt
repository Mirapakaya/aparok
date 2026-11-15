package com.aparokio.aparok.domain.misc

import com.aparokio.aparok.core_models.LinkPreview
import com.aparokio.aparok.core_models.Url
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetLinkPreview @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Url, LinkPreview>(dispatchers.io) {
    override suspend fun doWork(params: Url): LinkPreview {
        return repo.getLinkPreview(url = params)
    }
}