package com.example.rickandmortykotlin.presentation.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.repositories.LocationRepository

class LocationViewModel(
    private val repository: LocationRepository,
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)
}