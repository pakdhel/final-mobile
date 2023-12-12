package com.D121211009.nasa.data

import com.D121211009.nasa.data.repository.NasaRepository
import com.D121211009.nasa.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val nasaRepository: NasaRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.nasa.gov"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val nasaRepository: NasaRepository
        get() = NasaRepository(retrofitService)

}