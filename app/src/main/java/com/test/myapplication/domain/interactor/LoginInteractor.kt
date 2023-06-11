package com.test.myapplication.domain.interactor

import com.test.myapplication.domain.repository.LoginRepository

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class LoginInteractor(
    private val loginRepository: LoginRepository
) {
    suspend fun login(email: String, password: String) = loginRepository.login(email, password)
    suspend fun register(email: String, password: String, age: Int) =
        loginRepository.register(email, password, age)
}