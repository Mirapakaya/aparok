package com.aparokio.aparok.persistence.mapper

import com.aparokio.aparok.data.auth.model.AccountEntity
import com.aparokio.aparok.persistence.model.AccountTable

fun AccountTable.toEntity(): AccountEntity {
    return AccountEntity(
        id = id
    )
}

fun AccountEntity.toTable(): AccountTable {
    return AccountTable(
        id = id,
        timestamp = System.currentTimeMillis(),
        name = "",
        color = null
    )
}