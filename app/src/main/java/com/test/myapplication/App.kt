package com.test.myapplication

import android.app.Application
import com.test.myapplication.di.interactorsModule
import com.test.myapplication.di.networkModule
import com.test.myapplication.di.repositoriesModule
import com.test.myapplication.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(networkModule, repositoriesModule, viewModelsModule, interactorsModule)
        }
    }
}