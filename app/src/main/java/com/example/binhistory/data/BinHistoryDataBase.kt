package com.example.binhistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinHistoryEntity::class], version = 1, exportSchema = false)
abstract class BinHistoryDataBase: RoomDatabase() {
    abstract fun binHistoryDao(): BinHistoryDao

    companion object {
        @Volatile
        private var instance: BinHistoryDataBase? = null

        fun getInstance(context: Context): BinHistoryDataBase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    BinHistoryDataBase::class.java,
                    "bin_history_database"
                ).build().also { instance = it }
            }
        }
    }
}