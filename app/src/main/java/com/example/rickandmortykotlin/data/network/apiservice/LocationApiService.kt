package com.example.rickandmortykotlin.data.network.apiservice

import com.example.rickandmortykotlin.data.network.dto.RickAndMortyResponse
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocations(
        @Query("page") page: Int,
    ): RickAndMortyResponse<LocationDto>

    @GET("/api/location/{id}")
    suspend fun fetchLocation(
        @Path("id")id : Int
    ): LocationDto
}