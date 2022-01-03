package com.example.rickandmortykotlin.data.network.paginsources

import com.example.rickandmortykotlin.common.base.BasePagingSource
import com.example.rickandmortykotlin.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto

class CharacterPagingSource (
    private val service: CharacterApiService
) : BasePagingSource<CharacterDto>({ position ->
    service.fetchCharacters(position)
})