package com.aparokio.aparok.core_utils.ext

fun String?.orNull(): String? = this?.ifEmpty { null }