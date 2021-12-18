package com.example.rickandmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykotlin.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin.data.network.paginsources.CharacterPagingSource
import com.example.rickandmortykotlin.data.network.paginsources.EpisodePagingSource
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
) {

    fun fetchEpisodes(): LiveData<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }
}