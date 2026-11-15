package com.aparokio.aparok.emojifier.data

import com.aparokio.aparok.domain.icon.DocumentEmojiIconProvider

class DefaultDocumentEmojiIconProvider : DocumentEmojiIconProvider {

    companion object {
        val DOCUMENT_SET = listOf(
            "🌳", "⛳", "🧬", "🎈", "🎓",
            "💡", "🎒", "🚀", "🤖", "📚",
            "🍎", "🏡", "🤝", "😍", "☕",
            "🔥", "💥", "✍", "⏳", "📌",
            "🖍", "🦉", "📮", "📄", "🖌",
            "🗳", "⏰", "🔑", "🎉", "🗃",
            "🔖", "🧠", "👁", "🎗", "🎲",
            "🏙", "🚲", "⚙", "🔶", "🌍",
            "🏕", "🎡", "🌵", "🚗", "🚂",
            "🖼", "⭐", "🥁", "🛴", "🛫",
            "🏔", "🏗", "🛠", "🔍", "🕹",
            "🛋", "🎁", "🧮", "🏜", "🌋",
            "🎇", "🍏", "💫", "🌿", "🦊",
            "🍁", "🐎", "🍋", "🏍", "⛵"
        )
    }

    override fun random(): String = DOCUMENT_SET.random()
}