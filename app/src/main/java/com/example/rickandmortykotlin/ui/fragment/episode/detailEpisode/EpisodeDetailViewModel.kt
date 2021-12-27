package com.example.rickandmortykotlin.ui.fragment.episode.detailEpisode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykotlin.common.resource.Resource
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository,
) : ViewModel() {

    private val _episodeState = MutableStateFlow<Resource<EpisodeDto>>(Resource.Loading())
    val episodeState: StateFlow<Resource<EpisodeDto>> = _episodeState

    fun fetchEpisode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchEpisode(id).collect {
                _episodeState.value = it
            }
        }
    }
}
