package com.aparokio.aparok.domain.cover

interface CoverCollectionProvider {
    fun provide(): List<CoverImage>
}