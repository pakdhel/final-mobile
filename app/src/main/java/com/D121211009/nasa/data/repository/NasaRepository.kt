package com.D121211009.nasa.data.repository

import com.D121211009.nasa.data.response.GetNasaResponse
import com.D121211009.nasa.data.source.remote.ApiService

class NasaRepository(private val apiService: ApiService) {

    suspend fun getNasa(): GetNasaResponse {
        return apiService.getNasa()
    }
}