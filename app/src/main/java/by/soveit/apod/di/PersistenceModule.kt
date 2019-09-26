package by.soveit.apod.di

import androidx.room.Room
import by.soveit.apod.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val persistenceModule = module {

    single {
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java, "gallery")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().imageDao() }
}