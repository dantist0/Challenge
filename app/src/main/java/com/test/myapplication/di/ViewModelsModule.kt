package com.test.myapplication.di

import android.util.Log
import com.test.myapplication.ui.fragments.details.DetailsViewModel
import com.test.myapplication.ui.fragments.home.HomeViewModel
import com.test.myapplication.ui.fragments.login.LoginViewModel
import com.test.myapplication.ui.fragments.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
val viewModelsModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(imagesInteractor = get())
    }
    viewModel<DetailsViewModel> {
        DetailsViewModel(imagesInteractor = get())
    }
    viewModel<LoginViewModel> {
        LoginViewModel(loginInteractor = get())
    }

    viewModel<RegisterViewModel> {
        RegisterViewModel(loginInteractor = get())
    }
}