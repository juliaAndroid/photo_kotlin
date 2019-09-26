package by.soveit.apod.di

import by.soveit.apod.repository.ImageRepository
import org.koin.dsl.module

val imageRepositoryModule = module {

    single { ImageRepository(get(), get(), get()) }

}