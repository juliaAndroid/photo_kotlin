package by.morozova.apod.di

import by.morozova.apod.BuildConfig
import by.morozova.apod.api.client.ImageClient
import by.morozova.apod.api.service.ImageService
import by.morozova.apod.models.action.DateConverter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single<ImageService> {
        get<Retrofit>().create(ImageService::class.java)
    }

    single { ImageClient(get()) }
    single { DateConverter(BuildConfig.DATE_PATTERN_REQUEST) }

}

private class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalUrl = chain.request().url
        val urlWithApiKey = originalUrl
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.KEY)
            .build()
        val requestWithApiKey = chain.request()
            .newBuilder()
            .url(urlWithApiKey)
            .build()
        return chain.proceed(requestWithApiKey)
    }

}
