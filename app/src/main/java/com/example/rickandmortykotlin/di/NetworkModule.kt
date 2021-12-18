package com.example.rickandmortykotlin.di

import com.example.rickandmortykotlin.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiService() = retrofitClient.provideCharacterApiService()

    @Provides
    @Singleton
    fun provideLocationApiService() = retrofitClient.provideLocationApiService()

    @Provides
    @Singleton
    fun provideEpisodeApiService() = retrofitClient.provideEpisodeApiService()
}