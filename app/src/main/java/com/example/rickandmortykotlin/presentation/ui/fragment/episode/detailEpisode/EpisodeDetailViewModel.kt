package com.example.rickandmortykotlin.presentation.ui.fragment.episode.detailEpisode

import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EpisodeDetailViewModel(
    private val repository: EpisodeRepository,
) : BaseViewModel() {

    private val _episodeState = MutableStateFlow<UIState<EpisodeDto>>(UIState.Loading())
    val episodeState: StateFlow<UIState<EpisodeDto>> = _episodeState

    fun fetchEpisode(id: Int) {
        _episodeState.subscribeTo {
            repository.fetchEpisode(id)
        }
    }
}
