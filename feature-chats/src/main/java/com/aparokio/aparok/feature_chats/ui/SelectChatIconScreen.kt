package com.aparokio.aparok.feature_chats.ui

import androidx.compose.runtime.Composable
import com.aparokio.aparok.core_ui.widgets.EmojiPickerScreen
import com.aparokio.aparok.presentation.picker.EmojiPickerView

@Composable
fun SelectChatIconScreen(
    views: List<EmojiPickerView> = emptyList(),
    onEmojiClicked: (String) -> Unit,
    onQueryChanged: (String) -> Unit
) {
    EmojiPickerScreen(
        views = views,
        onEmojiClicked = onEmojiClicked,
        onQueryChanged = onQueryChanged
    )
}
