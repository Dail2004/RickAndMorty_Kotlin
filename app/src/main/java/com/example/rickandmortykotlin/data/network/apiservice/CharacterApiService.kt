package com.example.rickandmortykotlin.data.network.apiservice

import com.example.rickandmortykotlin.data.network.dto.RickAndMortyResponse
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharacterDto>
}