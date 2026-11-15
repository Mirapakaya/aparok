package com.aparokio.aparok.domain.gallery_experience

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class ImportExperience @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<ImportExperience.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        val command = Command.ImportExperience(
            space = params.spaceId,
            url = params.url,
            title = params.title,
            isNewSpace = params.isNewSpace
        )
        repo.importExperience(command)
    }

    data class Params(
        val spaceId: SpaceId,
        val url: String,
        val title: String,
        val isNewSpace: Boolean
    )
}