package com.aparokio.aparok.core_ui.tools

import android.text.TextWatcher

interface TextInputTextWatcher: TextWatcher {
    fun lock()
    fun unlock()
}