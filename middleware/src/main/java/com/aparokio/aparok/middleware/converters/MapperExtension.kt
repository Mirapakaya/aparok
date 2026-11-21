package com.aparokio.aparok.middleware.converters

import aparok.Event
import com.aparokio.aparok.data.auth.model.AccountEntity

fun Event.Account.Show.toAccountEntity(): AccountEntity {
    val acc = account
    checkNotNull(acc)
    return AccountEntity(
        id = acc.id
    )
}