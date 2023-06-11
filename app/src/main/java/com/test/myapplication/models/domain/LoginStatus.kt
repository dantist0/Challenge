package com.test.myapplication.models.domain

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
data class LoginStatus(
    val isSuccess: Boolean,
    val emailErrorMessage: String?,
    val passwordErrorMessage: String?
) {
    companion object {
        fun success() = LoginStatus(
            isSuccess = true,
            emailErrorMessage = null,
            passwordErrorMessage = null
        )
    }
}
