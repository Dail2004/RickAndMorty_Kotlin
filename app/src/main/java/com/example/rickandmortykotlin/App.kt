package com.example.rickandmortykotlin

import android.app.Application
import com.example.rickandmortykotlin.servicelocator.networkModule
import com.example.rickandmortykotlin.servicelocator.repositoriesModel
import com.example.rickandmortykotlin.servicelocator.viewModelModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModel, viewModelModule)
        }
    }
}