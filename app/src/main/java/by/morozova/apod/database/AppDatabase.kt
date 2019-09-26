package by.morozova.apod.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.morozova.apod.api.adapter.MediaTypeConverter
import by.morozova.apod.models.entity.ImageGallery

@Database(entities = [(ImageGallery::class)], version = 1)
@TypeConverters(MediaTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun imageDao() : ImageDao
}
