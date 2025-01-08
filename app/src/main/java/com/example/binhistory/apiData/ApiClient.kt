package com.example.binhistory.apiData

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .hostnameVerifier { _, _ -> true }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://binlist.net/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: Requests = retrofit.create(Requests::class.java)
}
