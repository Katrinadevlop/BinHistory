package com.example.binhistory.data

import com.example.binhistory.apiData.Requests

class BinHistoryRepository(
    private val api: Requests,
    private val dao: BinHistoryDao
) {
    suspend fun getBinInfo(bin: String): BinHistoryEntity {
        val response = api.getBinInfo(bin)
        val entity = BinHistoryEntity(
            bin = bin,
            country = response.country,
            coordinates = response.coordinates,
            cardType = response.cardType,
            bankName = response.bankInfo.city,
            bankUrl = response.bankInfo.url,
            bankPhone = response.bankInfo.phone,
            bankCity = response.bankInfo.city
        )
        dao.insertBinHistory(entity)
        return entity
    }

    suspend fun getBinHistory(): List<BinHistoryEntity> {
        return dao.getAllBinHistory()
    }

    suspend fun clearHistory() {
        dao.clearBinHistory()
    }
}