package com.aparokio.aparok.middleware.mappers

import aparok.Rpc.Unsplash.Search.Response.Picture
import com.aparokio.aparok.core_models.UnsplashImage

fun Picture.core() : UnsplashImage = UnsplashImage(
    id = id,
    url = url,
    artist = UnsplashImage.Artist(
        name = artist,
        url = artistUrl
    )
)