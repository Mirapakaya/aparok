package com.aparokio.aparok.data.auth.repo.clipboard

import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.domain.clipboard.Clip
import com.aparokio.aparok.domain.clipboard.Clipboard

class ClipboardDataRepository(
    private val factory: ClipboardDataStore.Factory
) : Clipboard {

    override suspend fun put(text: String, html: String?, blocks: List<Block>) {
        factory.storage.persist(
            blocks = blocks
        )
        factory.system.put(
            text = text,
            html = html,
            ignoreHtml = true
        )
    }

    override suspend fun blocks(): List<Block> {
        return factory.storage.fetch()
    }

    override suspend fun clip(): Clip? = factory.system.clip()
}