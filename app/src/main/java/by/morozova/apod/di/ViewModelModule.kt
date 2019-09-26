package by.morozova.apod.di

import by.morozova.apod.viewmodel.ImageInfoViewModel
import by.morozova.apod.viewmodel.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ImageViewModel(get()) }
    viewModel { ImageInfoViewModel(get()) }
}