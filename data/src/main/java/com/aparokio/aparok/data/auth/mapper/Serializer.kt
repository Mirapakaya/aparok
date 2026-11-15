package com.aparokio.aparok.data.auth.mapper

import com.aparokio.aparok.core_models.Block

interface Serializer {
    fun serialize(blocks: List<Block>): ByteArray
    fun deserialize(blob: ByteArray): List<Block>
}