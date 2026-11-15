package com.aparokio.aparok.domain.`object`

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.block.repo.BlockRepository

class DuplicateObject(
    private val repo: BlockRepository
) : BaseUseCase<Id, Id>() {

    override suspend fun run(params: Id) = safe {
        repo.duplicateObject(params)
    }
}