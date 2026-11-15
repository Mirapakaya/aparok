package com.aparokio.aparok.domain.gallery_experience

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.ManifestInfo
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class DownloadGalleryManifest @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<DownloadGalleryManifest.Params, ManifestInfo?>(dispatchers.io) {

    override suspend fun doWork(params: Params): ManifestInfo? {
        val command = Command.DownloadGalleryManifest(
            url = params.url
        )
        return repo.downloadGalleryManifest(command)
    }

    class Params(val url: String)
}