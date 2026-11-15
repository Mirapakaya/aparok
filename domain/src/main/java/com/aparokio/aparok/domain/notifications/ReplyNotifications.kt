package com.aparokio.aparok.domain.notifications

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.ResultInteractor
import com.aparokio.aparok.domain.block.repo.BlockRepository
import javax.inject.Inject

class ReplyNotifications @Inject constructor(
    private val repo: BlockRepository,
    dispatchers: AppCoroutineDispatchers,
): ResultInteractor<List<Id>, Unit>(dispatchers.io) {
    override suspend fun doWork(params: List<Id>) {
        repo.replyNotifications(notifications = params)
    }
}