package com.example.rickandmortykotlin.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykotlin.common.base.BaseRepository
import com.example.rickandmortykotlin.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin.data.network.paginsources.EpisodePagingSource
import kotlinx.coroutines.flow.Flow

class EpisodeRepository(
    private val service: EpisodeApiService,
) : BaseRepository() {

    fun fetchEpisodes(): Flow<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).flow
    }

    fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id)
    }
}