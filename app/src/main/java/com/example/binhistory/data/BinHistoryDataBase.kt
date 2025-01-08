package com.example.binhistory.data

import androidx.room.Database

@Database(entities = [BinHistoryEntity::class], version = 1, exportSchema = false)
class BinHistoryDataBase {
    abstract fun binHistoryDao(): BinHistoryDao


}