package by.soveit.apod.models.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShortImageInfo(
    @PrimaryKey
    val date: Long,
    val title: String,
    @SerializedName("media_type")
    val mediaType: MediaType,
    val url: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?
) : Parcelable
