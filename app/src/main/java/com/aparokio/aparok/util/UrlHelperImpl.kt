package com.aparokio.aparok.util

import android.webkit.URLUtil
import com.aparokio.aparok.presentation.util.UrlHelper

class UrlHelperImpl : UrlHelper {
    override fun isValidUrl(url: String): Boolean {
        return URLUtil.isValidUrl(url)
    }
}
