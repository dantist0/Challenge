package com.test.myapplication.ui.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.base.ui.BaseViewModel
import com.test.myapplication.domain.interactor.LoginInteractor
import com.test.myapplication.models.domain.RegisterStatus
import kotlinx.coroutines.launch

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class RegisterViewModel(
    private val loginInteractor: LoginInteractor
) : BaseViewModel() {
    private val _registerStatusLiveData = MutableLiveData<RegisterStatus>()
    private val _registerInProcessLiveData = MutableLiveData(false)

    val registerStatusLiveData: LiveData<RegisterStatus> = _registerStatusLiveData
    val registerInProgressLiveData: LiveData<Boolean> = _registerInProcessLiveData

    fun register(email: String, password: String, age: Int) {
        if (_registerInProcessLiveData.value == true) {
            return
        }
        _registerInProcessLiveData.value = true

        viewModelScope.launch {
            try {
                _registerStatusLiveData.value = loginInteractor.register(email, password, age)
            }finally {
                _registerInProcessLiveData.value = false
            }
        }
    }
}