package com.aparokio.aparok.domain.objects.options

import com.aparokio.aparok.core_models.DVFilter
import com.aparokio.aparok.core_models.DVFilterCondition
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relations
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class GetOptions(
    private val repo: BlockRepository
) : BaseUseCase<List<ObjectWrapper.Option>, GetOptions.Params>() {

    override suspend fun run(params: Params) = safe {
        val filters = buildList {
            add(
                DVFilter(
                    relation = Relations.LAYOUT,
                    condition = DVFilterCondition.EQUAL,
                    value = ObjectType.Layout.RELATION_OPTION.code.toDouble()
                )
            )
            add(
                DVFilter(
                    relation = Relations.IS_DELETED,
                    condition = DVFilterCondition.NOT_EQUAL,
                    value = true
                )
            )
            add(
                DVFilter(
                    relation = Relations.IS_ARCHIVED,
                    condition = DVFilterCondition.NOT_EQUAL,
                    value = true
                )
            )
            add(
                DVFilter(
                    relation = Relations.RELATION_KEY,
                    condition = DVFilterCondition.EQUAL,
                    value = params.relation
                )
            )
            add(
                DVFilter(
                    relation = Relations.SPACE_ID,
                    condition = DVFilterCondition.EQUAL,
                    value = params.space
                )
            )
        }
        repo.searchObjects(
            space = SpaceId(params.space),
            sorts = emptyList(),
            filters = filters,
            limit = 0,
            offset = 0,
            keys = listOf(
                Relations.ID,
                Relations.SPACE_ID,
                Relations.NAME,
                Relations.RELATION_OPTION_COLOR
            ),
            fulltext = params.fulltext,
        ).map {
            ObjectWrapper.Option(it)
        }
    }

    data class Params(val space: Id, val relation: Key, val fulltext: String = "")
}