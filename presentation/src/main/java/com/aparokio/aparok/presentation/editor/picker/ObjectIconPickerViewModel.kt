package com.aparokio.aparok.presentation.editor.picker

import androidx.lifecycle.viewModelScope
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.analytics.base.EventsDictionary
import com.aparokio.aparok.analytics.base.sendEvent
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.icon.RemoveIcon
import com.aparokio.aparok.domain.icon.SetEmojiIcon
import com.aparokio.aparok.domain.icon.SetImageIcon
import com.aparokio.aparok.emojifier.data.EmojiProvider
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.presentation.picker.IconPickerViewModel
import com.aparokio.aparok.presentation.util.Dispatcher
import kotlinx.coroutines.launch
import timber.log.Timber

class ObjectIconPickerViewModel<Iconable>(
    private val setEmojiIcon: SetEmojiIcon<Iconable>,
    private val setImageIcon: SetImageIcon<Iconable>,
    private val removeDocumentIcon: RemoveIcon<Iconable>,
    private val dispatcher: Dispatcher<Payload>,
    private val analytics: Analytics,
    provider: EmojiProvider,
    suggester: EmojiSuggester
) : IconPickerViewModel<Iconable>(provider, suggester) {

    override fun setEmoji(iconable: Iconable, emojiUnicode: String) {
        viewModelScope.launch {
            setEmojiIcon(
                params = SetEmojiIcon.Params(
                    emoji = emojiUnicode,
                    target = iconable
                )
            ).process(
                failure = { Timber.e(it, "Error while setting emoji") },
                success = { payload ->
                    sendEvent(
                        analytics = analytics,
                        eventName = EventsDictionary.objectSetIcon
                    )
                    if (payload.events.isNotEmpty()) dispatcher.send(payload)
                    state.value = ViewState.Exit
                }
            )
        }
    }

    override fun onRemoveClicked(iconable: Iconable) {
        viewModelScope.launch {
            removeDocumentIcon(iconable).process(
                failure = { Timber.e(it, "Error while removing icon") },
                success = { payload ->
                    sendEvent(
                        analytics = analytics,
                        eventName = EventsDictionary.objectRemoveIcon
                    )
                    if (payload.events.isNotEmpty()) dispatcher.send(payload)
                    state.value = ViewState.Exit
                }
            )
        }
    }

    override fun onPickedFromDevice(iconable: Iconable, path: String, space: Id) {
        viewModelScope.launch {
            state.value = ViewState.Loading
            setImageIcon(
                SetImageIcon.Params(
                    target = iconable,
                    path = path,
                    spaceId = SpaceId(space)
                )
            ).process(
                failure = {
                    Timber.e("Error while setting image icon")
                    state.value = ViewState.Init
                },
                success = { (payload, _) ->
                    dispatcher.send(payload)
                    state.value = ViewState.Exit
                }
            )
        }
    }

}