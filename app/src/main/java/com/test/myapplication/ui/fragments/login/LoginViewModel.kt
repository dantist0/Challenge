package com.test.myapplication.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.base.ui.BaseViewModel
import com.test.myapplication.domain.interactor.LoginInteractor
import com.test.myapplication.models.domain.LoginStatus
import kotlinx.coroutines.launch

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class LoginViewModel(private val loginInteractor: LoginInteractor) : BaseViewModel() {

    private val _loginStatusLiveData = MutableLiveData<LoginStatus>()
    private val _loginInProcessLiveData = MutableLiveData(false)

    val loginStatusLiveData: LiveData<LoginStatus> = _loginStatusLiveData
    val loginInProgressLiveData: LiveData<Boolean> = _loginInProcessLiveData

    fun login(email: String, password: String) {
        if (_loginInProcessLiveData.value == true) {
            return
        }
        _loginInProcessLiveData.value = true

        viewModelScope.launch {
            try {
                _loginStatusLiveData.value = loginInteractor.login(email, password)
            } finally {
                _loginInProcessLiveData.value = false
            }
        }
    }
}