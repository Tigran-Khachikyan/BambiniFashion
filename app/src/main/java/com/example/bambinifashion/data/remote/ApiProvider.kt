package com.example.bambinifashion.data.remote

import com.example.bambinifashion.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider(
    private val interceptors: List<Interceptor>,
    private val gson: Gson
) {
    companion object {
        private const val call_timeout = 60L
        private const val connection_timeout = 30L
        private const val read_timeout = 30L
        private const val write_timeout = 30L
    }

    private lateinit var api: ServiceApi

    fun init() {
        initMyServiceApi()
    }

    fun getMyServiceApi(): ServiceApi = api

    private fun initMyServiceApi() {
        api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .apply {
                        connectTimeout(connection_timeout, TimeUnit.SECONDS)
                        readTimeout(read_timeout, TimeUnit.SECONDS)
                        writeTimeout(write_timeout, TimeUnit.SECONDS)
                        callTimeout(call_timeout, TimeUnit.SECONDS)
                        interceptors.forEach { addInterceptor(it) }
                    }
                    .build()
            )
            .build()
            .create(ServiceApi::class.java)
    }
}
