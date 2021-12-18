package com.example.rickandmortykotlin.data.network

import com.example.rickandmortykotlin.common.constants.Constants
import com.example.rickandmortykotlin.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin.data.network.apiservice.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharacterApiService(): CharacterApiService = provideRetrofit.create(
        CharacterApiService::class.java
    )

    fun provideLocationApiService(): LocationApiService = provideRetrofit.create(
        LocationApiService::class.java
    )

    fun provideEpisodeApiService(): EpisodeApiService = provideRetrofit.create(
        EpisodeApiService::class.java
    )
}