package com.aparokio.aparok.domain.chats

import com.aparokio.aparok.domain.auth.repo.AuthRepository
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject

class SetRecentlyUsedChatReactions @Inject constructor(
    private val auth: AuthRepository,
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : ResultInteractor<Set<String>, Unit>(dispatchers.io) {

    override suspend fun doWork(params: Set<String>) {
        val acc = auth.getCurrentAccount()
        repo.setRecentlyUsedChatReactions(
            account = acc,
            emojis = params
        )
    }
}