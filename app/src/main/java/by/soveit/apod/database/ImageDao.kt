package by.soveit.apod.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.soveit.apod.models.entity.ImageGallery
import by.soveit.apod.models.entity.ShortImageInfo

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(images: List<ImageGallery>)

    @Query("SELECT * FROM IMAGE WHERE date = :dateId")
    fun getImageGalleryInfo(dateId: Long): ImageGallery

    @Query(
        "SELECT title, date, url, mediaType, thumbnailUrl FROM IMAGE " +
                "WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC"
    )
    fun getShortImageInfo(startDate: Long, endDate: Long): List<ShortImageInfo>

}