package com.aparokio.aparok.data.auth.other

interface ClipboardUriMatcher {
    fun isAparokUri(uri: String) : Boolean
}