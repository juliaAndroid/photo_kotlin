package by.morozova.apod.repository

import by.morozova.apod.api.ApiResult
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ) = when (val result = safeApiResult(call, errorMessage)) {
        is ApiResult.Success -> result.data
        is ApiResult.Error -> {
            null
        }
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): ApiResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) return ApiResult.Success(response.body()!!)
            ApiResult.Error(IOException("Error $errorMessage + ${response.errorBody()?.string()}"))
        } catch (e: IOException) {
            ApiResult.Error(IOException(e.message))
        }
    }
}