package com.aparokio.aparok.presentation.widgets.collection

data class CollectionSnackbar(
    val message: String,
    val action: () -> Unit = { }
)