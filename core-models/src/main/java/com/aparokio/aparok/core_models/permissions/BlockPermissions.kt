package com.aparokio.aparok.core_models.permissions

sealed class EditBlocksPermission {
    data object Edit : EditBlocksPermission()
    data object ReadOnly: EditBlocksPermission()
}