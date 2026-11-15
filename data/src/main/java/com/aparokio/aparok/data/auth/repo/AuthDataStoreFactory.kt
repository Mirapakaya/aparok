package com.aparokio.aparok.data.auth.repo

class AuthDataStoreFactory(
    val cache: AuthCacheDataStore,
    val remote: AuthRemoteDataStore
)