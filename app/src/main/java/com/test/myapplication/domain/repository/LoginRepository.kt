package com.test.myapplication.domain.repository

import com.test.myapplication.models.domain.LoginStatus
import com.test.myapplication.models.domain.RegisterStatus

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
interface LoginRepository {
    suspend fun login(email: String, password: String): LoginStatus
    suspend fun register(email: String, password: String, age: Int): RegisterStatus
}