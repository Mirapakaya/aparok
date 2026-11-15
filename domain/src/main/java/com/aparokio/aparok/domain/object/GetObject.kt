package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectView
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

/**
 * Use-case for opening an object as preview — without subscribing to its subsequent changes.
 * If you want to receive payload events, you should use [OpenObject] instead.
 */
class GetObject @Inject constructor(
    private val repo: BlockRepository,
    private val settings: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetObject.Params, ObjectView>(dispatchers.io) {
    override suspend fun doWork(params: Params): ObjectView = repo.getObject(
        id = params.target,
        space = params.space
    )

    data class Params(
        val target: Id,
        val space: SpaceId,
        val saveAsLastOpened: Boolean = false
    )
}