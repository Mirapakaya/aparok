package com.aparokio.aparok.domain.page

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id

/**
 * Use-case for updating document's title
 */
class UpdateTitle(
    private val repo: BlockRepository
) : BaseUseCase<Unit, UpdateTitle.Params>() {

    override suspend fun run(params: Params) = try {
        repo.updateDocumentTitle(
            command = Command.UpdateTitle(
                context = params.context,
                title = params.title
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    /**
     * Params for updating document's title
     * @property context id of the context
     * @property title new title for the document
     */
    data class Params(
        val context: Id,
        val title: String
    )
}