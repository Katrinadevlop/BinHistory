package com.example.binhistory.data

import android.util.Log
import com.example.binhistory.apiData.Requests

class BinHistoryRepository(
    private val api: Requests,
    private val dao: BinHistoryDao
) {
    suspend fun getBinInfo(bin: String): BinHistoryEntity? {
        val response = api.getBinInfo(bin)
        if (response.isSuccessful) {
            val binInfo = response.body()
            Log.d("BinHistoryRepository", "Response body: $binInfo")
        } else {
            Log.e("BinHistoryRepository", "Error response: ${response.code()}")
        }

        val entity = response.body()?.let { apiClient ->
            val country = apiClient.country?.name
            val coordinates = apiClient.coordinates
            val cardType = apiClient.scheme
            val bankInfo = apiClient.bankInfo

            if (country != null && cardType != null) {
                BinHistoryEntity(
                    bin = bin,
                    country = country,
                    coordinates = coordinates ?: "Не указано",
                    cardType = cardType,
                    bankName = bankInfo?.name ?: "Не указано",
                    bankUrl = bankInfo?.url ?: "Не указано",
                    bankPhone = bankInfo?.phone ?: "Не указано",
                    bankCity = bankInfo?.city ?: "Не указано"
                )
            } else {
                null
            }
        }

        if (entity != null) {
            dao.insertBinHistory(entity)
        }

        return entity
    }

    suspend fun getBinHistory(): List<BinHistoryEntity> {
        return dao.getAllBinHistory()
    }

    suspend fun clearHistory() {
        dao.clearBinHistory()
    }
}
