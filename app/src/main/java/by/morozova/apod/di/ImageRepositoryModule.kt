package by.morozova.apod.di

import by.morozova.apod.repository.ImageRepository
import org.koin.dsl.module

val imageRepositoryModule = module {

    single { ImageRepository(get(), get(), get()) }

}