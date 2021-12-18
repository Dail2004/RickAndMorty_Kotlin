package com.example.rickandmortykotlin.data.network.apiservice

import com.example.rickandmortykotlin.data.network.dto.RickAndMortyResponse
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocation(
        @Query("page") page: Int,
    ): RickAndMortyResponse<LocationDto>
}