package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class CreateRelationOption(
    private val repo: BlockRepository
) : BaseUseCase<ObjectWrapper.Option, CreateRelationOption.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.createRelationOption(
            space = params.space,
            name = params.name,
            relation = params.relation,
            color = params.color
        )
    }

    /**
     * [relation] relation key or id for new option
     */
    data class Params(
        val space: Id,
        val relation: Id,
        val name: String,
        val color: String,
    )
}