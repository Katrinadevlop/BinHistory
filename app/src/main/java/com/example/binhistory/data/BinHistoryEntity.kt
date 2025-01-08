package com.example.binhistory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_history")
data class BinHistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bin: String,
    val country: String,
    val coordinates: String,
    val cardType: String,
    val bankName: String,
    val bankUrl: String,
    val bankPhone: String,
    val bankCity: String
)