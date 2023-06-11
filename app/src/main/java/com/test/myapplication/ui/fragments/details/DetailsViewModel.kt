package com.test.myapplication.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.base.ui.BaseViewModel
import com.test.myapplication.domain.interactor.ImagesInteractor
import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.utils.repeatIfIOError
import kotlinx.coroutines.launch

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class DetailsViewModel(
    private val imagesInteractor: ImagesInteractor
) : BaseViewModel() {
    private val _isLoadingLiveData = MutableLiveData(false)
    private val _showErrorLiveData = MutableLiveData<Throwable?>(null)
    private val _imageLiveData = MutableLiveData<ImageData>()

    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    val showErrorLeveData: LiveData<Throwable?> = _showErrorLiveData
    val imageLiveData: LiveData<ImageData> = _imageLiveData

    fun load(imageId: Long) {
        if (_isLoadingLiveData.value == true) {
            return
        }
        _isLoadingLiveData.value = true

        viewModelScope.launch {
            try {
                repeatIfIOError(
                    doOnRepeat = { _showErrorLiveData.value = it },
                    doOnSuccess = { _showErrorLiveData.value = null }
                ) {
                    _imageLiveData.value = imagesInteractor.loadImage(imageId)
                }
            } finally {
                _isLoadingLiveData.value = false
            }
        }
    }
}