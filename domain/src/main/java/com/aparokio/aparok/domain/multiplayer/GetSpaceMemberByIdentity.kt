package com.aparokio.aparok.domain.multiplayer

import com.aparokio.aparok.core_models.DVFilter
import com.aparokio.aparok.core_models.DVFilterCondition
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class GetSpaceMemberByIdentity @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
): ResultInteractor<GetSpaceMemberByIdentity.Params, ObjectWrapper.SpaceMember?>(dispatchers.io) {

    override suspend fun doWork(params: Params): ObjectWrapper.SpaceMember? {
        val results = repo.searchObjects(
            space = params.space,
            limit = 0,
            filters = buildList {
                add(
                    DVFilter(
                        relation = Relations.LAYOUT,
                        value = ObjectType.Layout.PARTICIPANT.code.toDouble(),
                        condition = DVFilterCondition.EQUAL
                    )
                )
                add(
                    DVFilter(
                        relation = Relations.IDENTITY,
                        value = params.identity,
                        condition = DVFilterCondition.EQUAL
                    )
                )
            },
            keys = listOf(
                Relations.ID,
                Relations.IDENTITY,
                Relations.SPACE_ID,
                Relations.PARTICIPANT_PERMISSIONS,
                Relations.PARTICIPANT_STATUS,
                Relations.NAME,
                Relations.PLURAL_NAME,
                Relations.ICON_IMAGE,
                Relations.LAYOUT
            )
        )
        return if (results.isNotEmpty() && results.first().isNotEmpty()) {
            ObjectWrapper.SpaceMember(results.first())
        } else {
            null
        }
    }

    data class Params(val space: SpaceId, val identity: Id)
}