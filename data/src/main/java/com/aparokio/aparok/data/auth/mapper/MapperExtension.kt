package com.aparokio.aparok.data.auth.mapper

import com.aparokio.aparok.core_models.Account
import com.aparokio.aparok.data.auth.model.AccountEntity
import com.aparokio.aparok.data.auth.model.WalletEntity
import com.aparokio.aparok.domain.auth.model.Wallet

fun AccountEntity.toDomain(): Account {
    return Account(
        id = id
    )
}

fun Account.toEntity(): AccountEntity {
    return AccountEntity(
        id = id
    )
}

fun WalletEntity.toDomain(): Wallet {
    return Wallet(
        mnemonic = mnemonic
    )
}