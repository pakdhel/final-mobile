package com.D121211009.nasa.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String?,
    val rover: Rover,
    val sol: Int
) : Parcelable