package com.aparokio.aparok.core_ui.extensions

import com.aparokio.aparok.core_ui.common.isLinksOrMentionsPresent
import com.aparokio.aparok.core_ui.widgets.text.TextInputWidget
import com.aparokio.aparok.presentation.editor.editor.Markup

fun TextInputWidget.applyMovementMethod(item: Markup) {
    if (item.marks.isNotEmpty() && item.marks.isLinksOrMentionsPresent()) {
        setLinksClickable()
    } else {
        setDefaultMovementMethod()
    }
}

fun TextInputWidget.disable() {
    isEnabled = false
    pauseTextWatchers {
        enableReadMode()
    }
}