package com.aparokio.aparok.presentation.editor.editor

sealed class SideEffect {
    object ClearMultiSelectSelection : SideEffect()
    object ResetFocusToFirstTextBlock: SideEffect()
}