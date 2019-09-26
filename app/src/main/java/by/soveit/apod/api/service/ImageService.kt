package by.soveit.apod.api.service

import by.soveit.apod.models.entity.ImageGallery
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("planetary/apod")
    fun fetchImage(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Deferred<Response<List<ImageGallery>>>

    @GET("planetary/apod")
    fun fetchImage(@Query("count") count: Int): Deferred<Response<List<ImageGallery>>>
}