package com.example.binhistory.model

interface useBin{
    fun getBinInfo(bin:String):BinInfo
    fun setBinHistory(bin:String)
    fun getBinHistory(): List<String>
}

data class BinInfo (
    val country: String,
    val coordinates: String,
    val cardType:String,
    val bankInfo: BankInfo
)

data class BankInfo(
    val url: String,
    val phone: String,
    val website:String,
    val city:String
)