package com.D121211009.nasa.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Camera(
    val full_name: String,
    val id: Int,
    val name: String,
    val rover_id: Int
) : Parcelable