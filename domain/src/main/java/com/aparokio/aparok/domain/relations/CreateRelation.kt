package com.aparokio.aparok.domain.relations

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.core_models.Relation.Format.OBJECT
import com.aparokio.aparok.core_models.RelationFormat
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.objects.StoreOfRelations

/**
 * Use-case for creating a new relation object.
 */
class CreateRelation(
    private val repo: BlockRepository,
    private val storeOfRelations: StoreOfRelations
) : BaseUseCase<ObjectWrapper.Relation, CreateRelation.Params>() {

    override suspend fun run(params: Params) = safe {
        repo.createRelation(
            space = params.space,
            name = params.name,
            format = params.format,
            formatObjectTypes = params.limitObjectTypes,
            prefilled = params.prefilled
        ).also {
            // Workaround for making sure that our relations store contains new relation
            // Before client code process this use-case's response.
            storeOfRelations.merge(
                listOf(it)
            )
        }
    }

    /**
     * @param [name] name of the new relation
     * @param [limitObjectTypes] limit object types provided for [OBJECT] format.
     * @param [format] format of the new relation
     * @param [prefilled] (optional) pre-filled values for this relation
     */
    class Params(
        val space: Id,
        val name: String,
        val format: RelationFormat,
        val limitObjectTypes: List<Id>,
        val prefilled: Struct,
    )
}