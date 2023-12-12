package com.D121211009.nasa.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CameraX(
    val full_name: String,
    val name: String
) : Parcelable