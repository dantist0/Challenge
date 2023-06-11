package com.test.myapplication.di

import com.test.myapplication.domain.interactor.ImagesInteractor
import com.test.myapplication.domain.interactor.LoginInteractor
import org.koin.dsl.module

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
val interactorsModule = module {
    single<ImagesInteractor> {
        ImagesInteractor(imagesRepository = get())
    }

    single<LoginInteractor> { LoginInteractor(loginRepository = get()) }
}