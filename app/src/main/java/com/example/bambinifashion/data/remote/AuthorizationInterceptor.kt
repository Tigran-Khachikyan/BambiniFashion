package com.example.bambinifashion.data.remote

import com.example.bambinifashion.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent

class AuthorizationInterceptor : Interceptor, KoinComponent {
    companion object {
        private const val header_api_key= "bf-api-key"
        private const val header_localization = "bf-localization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return chain.proceed(
            originalRequest
                .newBuilder().apply {
                    header(header_api_key, BuildConfig.API_KEY)
                    header(header_localization, BuildConfig.API_LOCALIZATION)
                }.build()
        )
    }
}
