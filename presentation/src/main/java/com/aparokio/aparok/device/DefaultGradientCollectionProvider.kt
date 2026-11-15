package com.aparokio.aparok.device

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.cover.GradientCollectionProvider
import com.aparokio.aparok.presentation.editor.cover.CoverGradient

object DefaultGradientCollectionProvider : GradientCollectionProvider {
    override fun provide(): List<Id> = CoverGradient.default
}