package com.D121211009.nasa.data.source.remote

import com.D121211009.nasa.data.response.GetNasaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getNasa(
        @Query("sol") q: String = "1000",
        @Query("api_key") apiKey: String = "ervDCJmuFNhErtXypLpbcDesXkbO7oyP9eie0cQJ"
    ): GetNasaResponse
}