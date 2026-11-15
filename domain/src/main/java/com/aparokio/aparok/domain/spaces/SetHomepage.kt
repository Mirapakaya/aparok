package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class SetHomepage @Inject constructor(
    dispatchers: AppCoroutineDispatchers,
    private val repo: BlockRepository
) : ResultInteractor<SetHomepage.Params, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Params) {
        repo.setHomepage(
            Command.SetHomepage(
                spaceId = params.spaceId,
                homepage = params.homepage
            )
        )
    }

    data class Params(val spaceId: Id, val homepage: Id)
}
