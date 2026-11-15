package com.aparokio.aparok.ext

import com.aparokio.aparok.core_utils.date.isToday
import com.aparokio.aparok.core_utils.date.isTomorrow
import com.aparokio.aparok.domain.account.DateHelper

class DefaultDateHelper : DateHelper {
    override fun isToday(millis: Long): Boolean {
        return millis.isToday()
    }

    override fun isTomorrow(millis: Long): Boolean {
        return millis.isTomorrow()
    }
}