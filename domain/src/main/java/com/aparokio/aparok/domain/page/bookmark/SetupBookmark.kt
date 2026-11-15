package com.aparokio.aparok.domain.page.bookmark

import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either
import com.aparokio.aparok.core_models.Command
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload

/**
 * Use-case for setting up (i.e. fetching) a bookmark from url.
 */
class SetupBookmark(
    private val repo: BlockRepository
) : BaseUseCase<Payload, SetupBookmark.Params>() {

    override suspend fun run(params: Params) = try {
        repo.setupBookmark(
            command = Command.SetupBookmark(
                context = params.context,
                target = params.target,
                url = params.url
            )
        ).let {
            Either.Right(it)
        }
    } catch (t: Throwable) {
        Either.Left(t)
    }

    /**
     * Params for setting up a bookmark from [url]
     * @property context id of the context
     * @property target id of the target block (future bookmark block)
     * @property url bookmark url
     */
    data class Params(
        val context: Id,
        val target: Id,
        val url: String
    )
}