package by.soveit.apod.di

import by.soveit.apod.viewmodel.ImageInfoViewModel
import by.soveit.apod.viewmodel.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ImageViewModel(get()) }
    viewModel { ImageInfoViewModel(get()) }
}