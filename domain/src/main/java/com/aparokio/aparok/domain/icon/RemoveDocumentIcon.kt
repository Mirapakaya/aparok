package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.block.repo.BlockRepository

/**
 * Use-case for remove icon from object.
 */
class RemoveDocumentIcon(
    private val repo: BlockRepository
) : RemoveIcon<Id>() {

    override suspend fun run(params: Id) = safe {
        repo.removeDocumentIcon(params)
    }
}