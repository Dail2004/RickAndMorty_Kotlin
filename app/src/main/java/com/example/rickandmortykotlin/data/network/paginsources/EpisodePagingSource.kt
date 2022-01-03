package com.example.rickandmortykotlin.data.network.paginsources

import com.example.rickandmortykotlin.common.base.BasePagingSource
import com.example.rickandmortykotlin.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<EpisodeDto>({ position ->
    service.fetchEpisodes(position)
})