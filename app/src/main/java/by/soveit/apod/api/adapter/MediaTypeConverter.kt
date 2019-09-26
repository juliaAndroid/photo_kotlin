package by.soveit.apod.api.adapter

import androidx.room.TypeConverter
import by.soveit.apod.models.entity.MediaType


class MediaTypeConverter {

    @TypeConverter
    fun stringToMediaType(value: String): MediaType? {
        return MediaType.values().find { it.type == value }
    }

    @TypeConverter
    fun mediaTypeToString(type: MediaType): String {
        return type.type
    }
}