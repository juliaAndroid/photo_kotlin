package by.morozova.apod.api.client

import by.morozova.apod.api.service.ImageService
import by.morozova.apod.repository.BaseRepository

class ImageClient(private val api: ImageService) : BaseRepository() {

    suspend fun fetchImage(startDate: String, endDate: String) = safeApiCall(
        call = { api.fetchImage(startDate, endDate).await() },
        errorMessage = "Can't fetch IMAGE from $startDate to $endDate"
    )

    suspend fun fetchImage(count: Int) = safeApiCall(
        call = { api.fetchImage(count).await() },
        errorMessage = "Can't fetch images count $count"
    )
}