package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectView
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class OpenObject @Inject constructor(
    private val repo: BlockRepository,
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<OpenObject.Params, ObjectView>(dispatchers.io) {

    override suspend fun doWork(params: Params) = repo.openObject(
        id = params.obj,
        space = params.spaceId
    )

    data class Params(
        val obj: Id,
        val saveAsLastOpened: Boolean = true,
        val spaceId: SpaceId
    )
}