package by.morozova.apod

import android.app.Application
import by.morozova.apod.di.imageRepositoryModule
import by.morozova.apod.di.networkModule
import by.morozova.apod.di.persistenceModule
import by.morozova.apod.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApodApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApodApplication)
            modules(networkModule)
            modules(persistenceModule)
            modules(imageRepositoryModule)
            modules(viewModelModule)
        }
    }
}