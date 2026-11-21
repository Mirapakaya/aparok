package com.aparokio.aparok.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.aparokio.aparok.persistence.common.Config
import com.aparokio.aparok.persistence.common.Provider
import com.aparokio.aparok.persistence.dao.AccountDao
import com.aparokio.aparok.persistence.model.AccountTable

@Database(
    entities = [AccountTable::class],
    exportSchema = false,
    version = 1
)
abstract class AparokDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    companion object : Provider<Context, AparokDatabase>() {
        override fun create(param: Context) = databaseBuilder(
            param.applicationContext,
            AparokDatabase::class.java,
            Config.DATABASE_NAME
        ).build()
    }
}