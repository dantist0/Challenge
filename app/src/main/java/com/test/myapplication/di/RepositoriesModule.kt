package com.test.myapplication.di

import android.content.Context
import com.test.myapplication.data.converters.ImagesConverter
import com.test.myapplication.data.repository.ImagesRepositoryImpl
import com.test.myapplication.data.repository.MockedLoginRepositoryImpl
import com.test.myapplication.data.utils.InputValidator
import com.test.myapplication.domain.repository.ImagesRepository
import com.test.myapplication.domain.repository.LoginRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
val repositoriesModule = module {
    single<ImagesRepository> {
        ImagesRepositoryImpl(
            networkService = get(),
            imagesConverter = ImagesConverter()
        )
    }

    single<LoginRepository> {
        MockedLoginRepositoryImpl(
            inputValidator = get(),
            shaderPreference = androidApplication().getSharedPreferences(
                "MockedLoginRepository",
                Context.MODE_PRIVATE
            )
        )
    }

    single<InputValidator> { InputValidator(androidContext().resources) }
}