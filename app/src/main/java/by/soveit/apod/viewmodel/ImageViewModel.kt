package by.soveit.apod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.soveit.apod.models.entity.ShortImageInfo
import by.soveit.apod.repository.ImageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageViewModel(
    imageRepository: ImageRepository
) : ViewModel() {


    private val data = imageRepository.getImages(System.currentTimeMillis())
    private val _imageLiveData = MutableLiveData<List<ShortImageInfo>>()
    val imageLiveData = _imageLiveData as LiveData<List<ShortImageInfo>>
    private var error: Boolean = false

    fun loadNextPage() {
        GlobalScope.launch {
            error = data.nextPage() == null
            _imageLiveData.postValue(data.items)
        }
    }
}