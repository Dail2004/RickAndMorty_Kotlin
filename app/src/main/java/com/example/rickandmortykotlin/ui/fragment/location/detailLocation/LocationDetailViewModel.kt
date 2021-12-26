package com.example.rickandmortykotlin.ui.fragment.location.detailLocation

import androidx.lifecycle.ViewModel
import com.example.rickandmortykotlin.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    val repository: LocationRepository,
) : ViewModel() {
    fun fetchLocation(id: Int) = repository.fetchLocation(id)
}