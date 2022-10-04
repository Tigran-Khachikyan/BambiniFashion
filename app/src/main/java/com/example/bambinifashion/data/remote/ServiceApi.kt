package com.example.bambinifashion.data.remote

import com.example.bambinifashion.data.remote.dto.ContentResponse
import com.example.bambinifashion.data.remote.dto.PromotionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("./page:type=landing")
    suspend fun getContent(): Response<ContentResponse>

    @GET("./user:proline")
    suspend fun getPromotion(): Response<PromotionResponse>
}
