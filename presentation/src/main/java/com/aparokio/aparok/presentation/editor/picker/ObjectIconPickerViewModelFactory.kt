package com.aparokio.aparok.presentation.editor.picker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.domain.icon.RemoveDocumentIcon
import com.aparokio.aparok.domain.icon.SetDocumentEmojiIcon
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.presentation.util.Dispatcher

class ObjectIconPickerViewModelFactory(
    private val setEmojiIcon: SetDocumentEmojiIcon,
    private val setImageIcon: SetDocumentImageIcon,
    private val removeDocumentIcon: RemoveDocumentIcon,
    private val emojiSuggester: EmojiSuggester,
    private val emojiProvider: EmojiProvider,
    private val dispatcher: Dispatcher<Payload>,
    private val analytics: Analytics
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObjectIconPickerViewModel(
            setEmojiIcon = setEmojiIcon,
            setImageIcon = setImageIcon,
            removeDocumentIcon = removeDocumentIcon,
            suggester = emojiSuggester,
            provider = emojiProvider,
            dispatcher = dispatcher,
            analytics = analytics
        ) as T
    }
}

class ObjectSetIconPickerViewModelFactory(
    private val setEmojiIcon: SetDocumentEmojiIcon,
    private val setImageIcon: SetDocumentImageIcon,
    private val removeDocumentIcon: RemoveDocumentIcon,
    private val emojiSuggester: EmojiSuggester,
    private val emojiProvider: EmojiProvider,
    private val dispatcher: Dispatcher<Payload>,
    private val analytics: Analytics
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObjectIconPickerViewModel(
            setEmojiIcon = setEmojiIcon,
            setImageIcon = setImageIcon,
            removeDocumentIcon = removeDocumentIcon,
            suggester = emojiSuggester,
            provider = emojiProvider,
            dispatcher = dispatcher,
            analytics = analytics
        ) as T
    }
}