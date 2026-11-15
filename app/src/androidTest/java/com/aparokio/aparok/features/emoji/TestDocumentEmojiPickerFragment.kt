package com.aparokio.aparok.features.emoji

import com.aparokio.aparok.presentation.editor.picker.ObjectIconPickerViewModelFactory
import com.aparokio.aparok.ui.editor.modals.ObjectIconPickerFragment

class TestDocumentEmojiPickerFragment : ObjectIconPickerFragment() {
    init {
        factory = testViewModelFactory
    }

    override fun injectDependencies() {}
    override fun releaseDependencies() {}

    companion object {
        lateinit var testViewModelFactory: ObjectIconPickerViewModelFactory
    }
}