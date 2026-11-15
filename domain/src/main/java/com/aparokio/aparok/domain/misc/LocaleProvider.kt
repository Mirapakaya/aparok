package com.aparokio.aparok.domain.misc

import java.util.Locale

interface LocaleProvider {
    fun language() : String
    fun locale() : Locale
}