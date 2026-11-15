package com.aparokio.aparok.domain.base

import com.aparokio.aparok.domain.error.Error

@Deprecated(
    message = "Result is deprecated",
    replaceWith = ReplaceWith("Resultat", "com.aparokio.aparok.domain.base")
)
sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val error: Error) : Result<T>()
}