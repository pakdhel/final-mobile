package com.D121211009.nasa.data.response

import com.D121211009.nasa.data.models.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNasaResponse(
    @SerialName("photos")
    val photos: List<Photo>
)