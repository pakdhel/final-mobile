package com.D121211009.nasa.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Rover(
    val cameras: List<CameraX>,
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val max_date: String,
    val max_sol: Int,
    val name: String,
    val status: String,
    val total_photos: Int
) : Parcelable