package com.aparokio.aparok.clipboard

import android.content.Context
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.data.auth.mapper.Serializer
import com.aparokio.aparok.data.auth.repo.clipboard.ClipboardDataStore
import java.io.File
import timber.log.Timber

class AparokClipboardStorage(
    private val context: Context,
    private val serializer: Serializer
) : ClipboardDataStore.Storage {

    override fun persist(blocks: List<Block>) {
        val serialized = serializer.serialize(blocks)
        context.openFileOutput(CLIPBOARD_FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(serialized)
            it.flush()
        }
    }

    override fun fetch(): List<Block> {
        val file = File(context.filesDir, CLIPBOARD_FILE_NAME)
        if (!file.exists()) {
            Timber.e( "ClipboardStorage, file does not exist: $file")
            return emptyList()
        }
        val stream = context.openFileInput(CLIPBOARD_FILE_NAME)
        val blob = stream.use { it.readBytes() }
        return serializer.deserialize(blob)
    }

    companion object {
        const val CLIPBOARD_FILE_NAME = "aparok_clipboard"
    }
}