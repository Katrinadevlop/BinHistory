package com.example.binhistory.apiData

import com.example.binhistory.model.BinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface Requests {
    @GET("/{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String):BinInfo
}