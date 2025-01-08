package com.example.binhistory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinHistory(binHistory: BinHistoryEntity)

    @Query("SELECT * FROM bin_history ORDER BY id DESC")
    suspend fun getAllBinHistory(): List<BinHistoryEntity>

    @Query("DELETE FROM bin_history")
    suspend fun clearBinHistory()
}