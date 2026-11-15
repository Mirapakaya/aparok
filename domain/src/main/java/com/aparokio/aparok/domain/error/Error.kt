package com.aparokio.aparok.domain.error

sealed class Error {
    object BackwardCompatibility : Error()
    object NotFoundObject : Error()
}