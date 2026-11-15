package com.aparokio.aparok.domain.config

interface Gateway {
    fun provide(): String
}