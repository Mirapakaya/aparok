package com.aparokio.aparok.presentation.editor.cover

import com.aparokio.aparok.core_models.Hash
import com.aparokio.aparok.core_models.Id


interface CoverImageHashProvider {
    fun provide(id: Id): Hash?
}