package com.aparokio.aparok.presentation.relations

import com.aparokio.aparok.core_models.Relation

data class RelationFormatView(
    val format: Relation.Format,
    val isSelected: Boolean = false
)