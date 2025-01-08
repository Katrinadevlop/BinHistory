package com.example.binhistory.apiData

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class OkHttp {
    val okHttpClient = OkHttpClient.Builder()
        .hostnameVerifier { _, _ -> true }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://binlist.net/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(Requests::class.java)

    fun createUnsafeTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}