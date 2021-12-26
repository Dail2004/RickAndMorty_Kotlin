package com.example.rickandmortykotlin.ui.fragment.episode.detailEpisode

import androidx.lifecycle.ViewModel
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository,
) : ViewModel() {
    fun fetchEpisode(id: Int) = repository.fetchEpisode(id)
}