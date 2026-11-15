package com.aparokio.aparok.domain.clipboard

import com.aparokio.aparok.core_models.Block

interface Clipboard {
    /**
     * @param text plain text to put on the clipboard
     * @param html optional html to put on the clipboard
     * @param blocks Aparok blocks to store on the clipboard)
     */
    suspend fun put(text: String, html: String?, blocks: List<Block>)

    /**
     * @return Aparok blocks currently stored on (or linked to) the clipboard
     */
    suspend fun blocks() : List<Block>

    /**
     * @return return current clip on the clipboard
     */
    suspend fun clip() : Clip?

    interface UriMatcher {
        /**
         * Checks whether this [uri] is internal Aparok clipboard URI.
         */
        fun isAparokUri(uri: String) : Boolean
    }
}