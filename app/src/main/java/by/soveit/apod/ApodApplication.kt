package by.soveit.apod

import android.app.Application
import by.soveit.apod.di.imageRepositoryModule
import by.soveit.apod.di.networkModule
import by.soveit.apod.di.persistenceModule
import by.soveit.apod.di.viewModelModule
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