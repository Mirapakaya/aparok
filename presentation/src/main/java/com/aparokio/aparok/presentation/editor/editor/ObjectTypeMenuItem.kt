package com.aparokio.aparok.presentation.editor.editor

sealed class ObjectTypeMenuItem {
    object ChangeType: ObjectTypeMenuItem()
    object OpenType: ObjectTypeMenuItem()
}
