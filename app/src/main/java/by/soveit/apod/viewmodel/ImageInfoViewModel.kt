package by.soveit.apod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.soveit.apod.models.entity.ImageGallery
import by.soveit.apod.repository.ImageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageInfoViewModel(
    imageRepository: ImageRepository
) : ViewModel() {

    var id: Long = 0
    private val _imageLiveData = MutableLiveData<ImageGallery>()
    val imageLiveData = _imageLiveData as LiveData<ImageGallery>

    init {
        GlobalScope.launch {
            _imageLiveData.postValue(imageRepository.getFullInfoById(id))
        }
    }

}