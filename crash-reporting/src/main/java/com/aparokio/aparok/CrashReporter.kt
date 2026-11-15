package com.aparokio.aparok

interface CrashReporter {
    fun init()
    fun setUser(userId: String)
}