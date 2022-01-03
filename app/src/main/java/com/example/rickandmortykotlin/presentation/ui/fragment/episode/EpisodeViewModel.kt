package com.example.rickandmortykotlin.presentation.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository

class EpisodeViewModel(
    private val repository: EpisodeRepository,
) : BaseViewModel() {

    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)
}