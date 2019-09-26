package by.morozova.apod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.morozova.apod.models.entity.ImageGallery
import by.morozova.apod.repository.ImageRepository
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