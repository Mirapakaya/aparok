package com.aparokio.aparok.presentation.editor.model

sealed class EditorFooter {
    object Note : EditorFooter()
    object None : EditorFooter()
}