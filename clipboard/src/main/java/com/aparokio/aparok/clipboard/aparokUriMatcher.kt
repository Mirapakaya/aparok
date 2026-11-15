package com.aparokio.aparok.clipboard

import com.aparokio.aparok.data.auth.other.ClipboardUriMatcher

class AparokUriMatcher : ClipboardUriMatcher {
    override fun isAparokUri(uri: String): Boolean {
        return uri == BuildConfig.APAROK_CLIPBOARD_URI
    }
}