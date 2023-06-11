package com.test.myapplication.models.domain

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
data class RegisterStatus(
    val isSuccess: Boolean,
    val emailErrorMessage: String?,
    val passwordErrorMessage: String?,
    val ageErrorMessage: String?,
){
    companion object{
        fun success() = RegisterStatus(
            isSuccess = true,
            emailErrorMessage = null,
            passwordErrorMessage = null,
            ageErrorMessage = null
        )
    }
}
