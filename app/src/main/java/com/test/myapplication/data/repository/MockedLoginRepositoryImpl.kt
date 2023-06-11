package com.test.myapplication.data.repository

import android.content.SharedPreferences
import com.test.myapplication.data.utils.InputValidator
import com.test.myapplication.domain.repository.LoginRepository
import com.test.myapplication.models.domain.LoginStatus
import com.test.myapplication.models.domain.RegisterStatus
import kotlinx.coroutines.delay

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class MockedLoginRepositoryImpl(
    private val shaderPreference: SharedPreferences,
    private val inputValidator: InputValidator
) : LoginRepository {
    override suspend fun login(email: String, password: String): LoginStatus {
        val emailValidationResult = inputValidator.validateEmail(email)
        val passwordValidationResult = inputValidator.validatePassword(password)
        val isAllValid = emailValidationResult.isValid && passwordValidationResult.isValid
        if (!isAllValid) {
            return LoginStatus(
                isSuccess = false,
                emailErrorMessage = emailValidationResult.errorMessage,
                passwordErrorMessage = passwordValidationResult.errorMessage
            )
        }

        delay(1000)

        if (!shaderPreference.contains(email)) {
            return LoginStatus(
                isSuccess = false,
                emailErrorMessage = "Email does not exist",
                passwordErrorMessage = null
            )
        }

        if (shaderPreference.getString(email, null) == password) {
            return LoginStatus.success()
        }

        return LoginStatus(
            isSuccess = false,
            emailErrorMessage = null,
            passwordErrorMessage = "Incorrect password"
        )
    }

    override suspend fun register(email: String, password: String, age: Int): RegisterStatus {

        val emailValidationResult = inputValidator.validateEmail(email)
        val passwordValidationResult = inputValidator.validatePassword(password)
        val ageValidationResult = inputValidator.validateAge(age)
        val isAllValid =
            emailValidationResult.isValid && passwordValidationResult.isValid && ageValidationResult.isValid

        if (!isAllValid) {
            return RegisterStatus(
                isSuccess = false,
                emailErrorMessage = emailValidationResult.errorMessage,
                passwordErrorMessage = passwordValidationResult.errorMessage,
                ageErrorMessage = ageValidationResult.errorMessage,
            )
        }

        delay(1000)

        if (shaderPreference.contains(email)) {
            return RegisterStatus(
                isSuccess = false,
                emailErrorMessage = "Email already exist",
                passwordErrorMessage = null,
                ageErrorMessage = null
            )
        }

        shaderPreference.edit().putString(email, password).apply()

        return RegisterStatus.success()
    }

}