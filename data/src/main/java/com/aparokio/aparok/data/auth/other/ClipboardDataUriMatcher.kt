package com.aparokio.aparok.data.auth.other

import com.aparokio.aparok.domain.clipboard.Clipboard

class ClipboardDataUriMatcher(
    private val matcher: ClipboardUriMatcher
) : Clipboard.UriMatcher {

    override fun isAparokUri(
        uri: String
    ): Boolean = matcher.isAparokUri(uri)
}