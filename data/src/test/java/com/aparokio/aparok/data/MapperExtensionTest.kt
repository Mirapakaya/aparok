package com.aparokio.aparok.data

import com.aparokio.aparok.core_models.Account
import com.aparokio.aparok.data.auth.mapper.toDomain
import com.aparokio.aparok.data.auth.mapper.toEntity
import com.aparokio.aparok.data.auth.model.AccountEntity
import com.aparokio.aparok.data.auth.model.WalletEntity
import com.aparokio.aparok.test_utils.MockDataFactory
import org.junit.Test
import kotlin.test.assertTrue

class MapperExtensionTest {

    @Test
    fun `should map correctly wallet from data to domain layer`() {

        val wallet = WalletEntity(
            mnemonic = MockDataFactory.randomString()
        )

        val result = wallet.toDomain()

        assertTrue { result.mnemonic == wallet.mnemonic }
    }

    @Test
    fun `should map correctly account from domain to data layer`() {

        val account = Account(
            id = MockDataFactory.randomUuid()
        )

        account.toEntity().let { result ->
            assertTrue { result.id == account.id }
        }
    }

    @Test
    fun `should map correctly account from data to domain layer`() {

        val account = AccountEntity(
            id = MockDataFactory.randomUuid()
        )

        account.toDomain().let { result ->
            assertTrue { result.id == account.id }
        }
    }
}