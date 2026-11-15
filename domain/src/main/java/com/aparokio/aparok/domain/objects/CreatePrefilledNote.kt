package com.aparokio.aparok.domain.objects

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.NO_VALUE
import com.aparokio.aparok.core_models.ObjectTypeUniqueKeys
import com.aparokio.aparok.core_models.Position
import com.aparokio.aparok.core_models.Struct
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeKey
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class CreatePrefilledNote @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<CreatePrefilledNote.Params, Id>(dispatchers.io) {

    override suspend fun doWork(params: Params): Id {
        val obj = repo.createObject(
            Command.CreateObject(
                typeKey = params.customType ?: TypeKey(ObjectTypeUniqueKeys.NOTE),
                space = SpaceId(params.space),
                template = null,
                internalFlags = emptyList(),
                prefilled = params.details
            )
        )
        repo.create(
            command = Command.Create(
                context = obj.id,
                prototype = Block.Prototype.Text(
                    style = Block.Content.Text.Style.P,
                    text = params.text
                ),
                position = Position.NONE,
                target = NO_VALUE
            )
        )
        params.attachments.forEach { attachment ->
            repo.create(
                command = Command.Create(
                    context = obj.id,
                    prototype = Block.Prototype.Link(
                        target = attachment,
                        cardStyle = Block.Content.Link.CardStyle.CARD
                    ),
                    position = Position.NONE,
                    target = NO_VALUE
                )
            )
        }
        return obj.id
    }

    /**
     * @param [customType] provide custom type instead of note if needed
     */
    data class Params(
        val space: Id,
        val text: String,
        val details: Struct,
        val customType: TypeKey? = null,
        val attachments: List<Id> = emptyList()
    )
}