package com.aparokio.aparok.presentation.widgets.collection

import com.aparokio.aparok.domain.base.Resultat
import com.aparokio.aparok.presentation.objects.ObjectAction

class CollectionUiState(
    val views: Resultat<List<CollectionView>>,
    val showEditMode: Boolean,
    val showWidget: Boolean,
    val collectionName: String,
    val actionName: String,
    val objectActions: List<ObjectAction>,
    val inDragMode: Boolean,
    val displayType: Boolean,
    val operationInProgress: Boolean,
    val isActionButtonVisible: Boolean
)