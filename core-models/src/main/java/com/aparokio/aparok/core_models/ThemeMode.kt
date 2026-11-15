package com.aparokio.aparok.core_models

sealed class ThemeMode {
    object System : ThemeMode()
    object Light : ThemeMode()
    object Night : ThemeMode()
}