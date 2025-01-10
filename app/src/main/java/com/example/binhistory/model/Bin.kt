package com.example.binhistory.model

data class Country(
    val name: String?,
    val code: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfo(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)

data class BinInfo(
    val country: Country?,
    val coordinates: String?,
    val scheme: String?,
    val bankInfo: BankInfo?
)
