package com.aparokio.aparok.core_ui.menu

import android.widget.TextView

sealed class AparokContextMenuEvent {
    object Detached : AparokContextMenuEvent()
    object MarkupChanged: AparokContextMenuEvent()
    data class Create(val view: TextView, val type: ContextMenuType) :
        AparokContextMenuEvent()

    data class Selected(val view: TextView, val type: ContextMenuType) :
        AparokContextMenuEvent()
}