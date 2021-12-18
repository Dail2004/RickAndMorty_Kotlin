package com.example.rickandmortykotlin.ui.fragment.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : ViewModel() {
    fun fetchEpisode() = repository.fetchEpisodes().cachedIn(viewModelScope)
}