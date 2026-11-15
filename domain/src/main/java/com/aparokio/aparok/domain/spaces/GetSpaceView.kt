package com.aparokio.aparok.domain.spaces

import com.aparokio.aparok.core_models.DVFilter
import com.aparokio.aparok.core_models.DVFilterCondition
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.config.ConfigStorage
import javax.inject.Inject

class GetSpaceView @Inject constructor(
    private val repo: BlockRepository,
    private val configStorage: ConfigStorage,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<GetSpaceView.Params, ObjectWrapper.Basic?>(dispatchers.io) {

    override suspend fun doWork(params: Params): ObjectWrapper.Basic? {
        when(params) {
            is Params.BySpaceViewId -> {
                val techSpace = configStorage.getOrNull()?.techSpace ?: return null
                val result = repo.searchObjects(
                    space = SpaceId(techSpace),
                    filters = buildList {
                        add(
                            DVFilter(
                                relation = Relations.ID,
                                value = params.spaceViewId,
                                condition = DVFilterCondition.EQUAL
                            )
                        )
                    },
                    limit = 1
                )
                return if (result.isNotEmpty()) {
                    ObjectWrapper.Basic(result.first())
                } else {
                    null
                }
            }
            is Params.BySpaceId -> {
                val result = repo.searchObjects(
                    space = params.space,
                    filters = buildList {
                        add(
                            DVFilter(
                                relation = Relations.TARGET_SPACE_ID,
                                value = params.space.id,
                                condition = DVFilterCondition.EQUAL
                            )
                        )
                    },
                    limit = 1
                )
                return if (result.isNotEmpty()) {
                    ObjectWrapper.Basic(result.first())
                } else {
                    null
                }
            }
        }
    }

    sealed class Params {
        data class BySpaceViewId(val spaceViewId: Id) : Params()
        data class BySpaceId(val space: SpaceId) : Params()
    }
}