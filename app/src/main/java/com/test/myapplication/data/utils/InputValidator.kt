package com.test.myapplication.data.utils

import android.content.res.Resources
import android.util.Patterns
import com.test.myapplication.R

private val PASSWORD_RANGE = 6..12
private val AGE_RANGE = 18..99

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class InputValidator(private val resources: Resources) {

    fun validateEmail(email: String): ValidateResult {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val message = if (isValid) {
            null
        } else {
            resources.getString(R.string.validation_message_email_invalid)
        }
        return ValidateResult(isValid, message)
    }

    fun validatePassword(password: String): ValidateResult {
        val isValid = password.length in PASSWORD_RANGE
        val message = if (isValid) {
            null
        } else {
            resources.getString(
                R.string.validation_message_password_length_error,
                PASSWORD_RANGE.first,
                PASSWORD_RANGE.last
            )
        }
        return ValidateResult(isValid, message)
    }

    fun validateAge(age: Int): ValidateResult {
        val isValid = age in 18..99
        val message = if (isValid) {
            null
        } else {
            resources.getString(
                R.string.validation_message_age_length_error,
                AGE_RANGE.first,
                AGE_RANGE.last
            )
        }
        return ValidateResult(isValid, message)
    }
}

data class ValidateResult(
    val isValid: Boolean,
    val errorMessage: String?
)