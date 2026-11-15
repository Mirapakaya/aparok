package com.aparokio.aparok.core_models.exceptions

sealed class LoginException : Exception() {
    class InvalidMnemonic : LoginException()
    class NetworkIdMismatch: LoginException()
    class FailedToFindAccountInfo: LoginException()
}