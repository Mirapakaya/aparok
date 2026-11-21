package com.aparokio.aparok.core_models.settings

import com.aparokio.aparok.core_models.DEFAULT_RELATIVE_DATES
import com.aparokio.aparok.core_models.FALLBACK_DATE_PATTERN
import com.aparokio.aparok.core_models.Id

data class VaultSettings(
    val isRelativeDates: Boolean,
    val dateFormat: String
) {
    companion object {
        fun default() : VaultSettings = VaultSettings(
            isRelativeDates = DEFAULT_RELATIVE_DATES,
            dateFormat = FALLBACK_DATE_PATTERN
        )
    }
}