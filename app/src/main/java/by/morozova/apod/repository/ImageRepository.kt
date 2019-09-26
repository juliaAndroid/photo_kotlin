package by.morozova.apod.repository

import by.morozova.apod.api.client.ImageClient
import by.morozova.apod.database.ImageDao
import by.morozova.apod.models.action.DateConverter
import by.morozova.apod.models.entity.ShortImageInfo
import by.morozova.apod.paging.Paging
import by.morozova.apod.repository.action.findMissingImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

private const val PAGE_SIZE: Long = 20

class ImageRepository constructor(
    private val imageClient: ImageClient,
    private val imageDao: ImageDao,
    private val dateConverter: DateConverter
) {


    fun getImages(endDate: Long): Paging<ShortImageInfo> = Paging { loaded ->
        if (loaded.isEmpty())
            loadImages(endDate - TimeUnit.DAYS.toMillis(PAGE_SIZE), endDate)
        else {
            val lastDate = getLastLoadedDate(loaded) - TimeUnit.DAYS.toMillis(1)
            loadImages(lastDate - TimeUnit.DAYS.toMillis(PAGE_SIZE), lastDate)
        }
    }

    private fun getLastLoadedDate(list: List<ShortImageInfo>): Long {
        return list.minBy { it.date }?.date ?: Long.MAX_VALUE
    }

    private suspend fun loadImages(startDate: Long, endDate: Long) = withContext(Dispatchers.IO) {
        val cached = imageDao.getShortImageInfo(startDate, endDate)
        if (cached.isEmpty()) {
            val dataFromServer =
                imageClient.fetchImage(
                    dateConverter.longDateToString(startDate),
                    dateConverter.longDateToString(endDate)
                )
            if (dataFromServer != null) {
                imageDao.insertImages(dataFromServer)
                val valid = imageDao.getShortImageInfo(startDate, endDate)
                valid
            } else {
                cached
            }
        } else {
            val toLoad: List<Pair<Long, Long>> =
                findMissingImages(cached, startDate, endDate)
            if (toLoad.isEmpty()) {
                cached
            } else {
                for (load in toLoad) {
                    val list = imageClient.fetchImage(
                        dateConverter.longDateToString(load.first),
                        dateConverter.longDateToString(load.second)
                    )
                    list?.let { imageDao.insertImages(it) }
                }
                val valid = imageDao.getShortImageInfo(startDate, endDate)
                valid
            }
        }

    }

    suspend fun getFullInfoById(id: Long) = withContext(Dispatchers.IO) {
        imageDao.getImageGalleryInfo(id)
    }
}
