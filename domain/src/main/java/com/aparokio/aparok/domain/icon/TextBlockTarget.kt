package com.aparokio.aparok.domain.icon

import com.aparokio.aparok.core_models.Id

data class TextBlockTarget(
    val context: Id,
    val blockId: Id,
)