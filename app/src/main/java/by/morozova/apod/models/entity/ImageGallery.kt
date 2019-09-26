package by.morozova.apod.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.morozova.apod.api.adapter.DateDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "IMAGE")
data class ImageGallery(
    val resource: String?,
    @SerializedName("concept_tags")
    val conceptTags: Boolean,
    val title: String,
    @JsonAdapter(DateDeserializer::class)
    @PrimaryKey
    val date: Long,
    val url: String,
    val hdurl: String?,
    @SerializedName("media_type")
    val mediaType: MediaType,
    val explanation: String,
    val concepts: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?
) : Parcelable