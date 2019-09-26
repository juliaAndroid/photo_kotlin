package by.morozova.apod.models.entity

import com.google.gson.annotations.SerializedName

enum class MediaType(val type: String) {
    @SerializedName("video")
    VIDEO("VIDEO"),
    @SerializedName("image")
    IMAGE("IMAGE")
}