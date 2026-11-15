package com.aparokio.aparok.middleware.converters

import aparok.clipboard.Clipboard
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.data.auth.mapper.Serializer
import com.aparokio.aparok.middleware.mappers.toCoreModels
import com.aparokio.aparok.middleware.mappers.toMiddlewareModel

class ClipboardSerializer : Serializer {

    override fun serialize(blocks: List<Block>): ByteArray {
        val models = blocks.map { it.toMiddlewareModel() }
        val clipboard = Clipboard(blocks = models)
        return Clipboard.ADAPTER.encode(clipboard)
    }

    override fun deserialize(blob: ByteArray): List<Block> {
        return Clipboard.ADAPTER.decode(blob).blocks.toCoreModels()
    }
}