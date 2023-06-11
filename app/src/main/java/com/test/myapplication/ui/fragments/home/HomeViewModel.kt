package com.test.myapplication.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.base.ui.BaseViewModel
import com.test.myapplication.domain.interactor.ImagesInteractor
import com.test.myapplication.models.domain.ImageData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class HomeViewModel(private val imagesInteractor: ImagesInteractor) : BaseViewModel() {

    private val _imagesListLiveData = MutableLiveData(emptyList<ImageData>())

    private val _isLoadingLiveData = MutableLiveData(false)
    private val _showErrorLiveData = MutableLiveData<Throwable?>(null)

    val imagesListLiveData: LiveData<List<ImageData>> = _imagesListLiveData
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    val showErrorLiveData: LiveData<Throwable?> = _showErrorLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _showErrorLiveData.value = throwable
    }

    private var nextPage = 1

    fun loadNewData() {
        if (_isLoadingLiveData.value == true) {
            return
        }
        _showErrorLiveData.value = null
        _isLoadingLiveData.value = true

        viewModelScope.launch(exceptionHandler) {
            try {
                val lastValue = _imagesListLiveData.value ?: emptyList()
                _imagesListLiveData.value = lastValue + imagesInteractor.loadImages("flowers", nextPage).images
            } finally {
                _isLoadingLiveData.value = false
                nextPage++
            }
        }
    }
}