package com.aparokio.aparok.domain.device

interface PathProvider {
    fun providePath(): String
    fun cachePath(): String
}