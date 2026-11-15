package com.aparokio.aparok.data.auth.model

import com.aparokio.aparok.domain.clipboard.Clip

/**
 * @see Clip
 */
class ClipEntity(
    override val text: String,
    override val html: String?,
    override val uri: String?
) : Clip