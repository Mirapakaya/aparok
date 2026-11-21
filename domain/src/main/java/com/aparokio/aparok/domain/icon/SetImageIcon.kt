package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.BaseUseCase

abstract class SetImageIcon<T> : BaseUseCase<Pair<Payload, Hash>, SetImageIcon.Params<T>>() {
    /**
     * Params for for setting document's image icon
     * @property path image path in file system
     * @property target i.e. id of the context or text blockId
     */
    data class Params<T>(
        val target: T,
        val path: String,
        val spaceId: SpaceId
    )
}