package com.aparokio.aparok.emojifier.suggest.data

import com.aparokio.aparok.emojifier.suggest.model.EmojiSuggest

interface EmojiSuggestStorage {
    suspend fun fetch(): List<EmojiSuggest>
}