package com.example.rickandmortykotlin.ui.fragment.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin.data.repositories.CharacterRepository
import com.example.rickandmortykotlin.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {
    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)
}