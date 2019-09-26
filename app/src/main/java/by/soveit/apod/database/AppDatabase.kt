package by.soveit.apod.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.soveit.apod.api.adapter.MediaTypeConverter
import by.soveit.apod.models.entity.ImageGallery

@Database(entities = [(ImageGallery::class)], version = 1)
@TypeConverters(MediaTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun imageDao() : ImageDao
}
