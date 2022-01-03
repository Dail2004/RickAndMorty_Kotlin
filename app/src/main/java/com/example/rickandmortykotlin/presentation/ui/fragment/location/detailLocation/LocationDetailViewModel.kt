package com.example.rickandmortykotlin.presentation.ui.fragment.location.detailLocation

import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin.data.repositories.LocationRepository
import com.example.rickandmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationDetailViewModel(
    private val repository: LocationRepository,
) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<LocationDto>>(UIState.Loading())
    val locationState: StateFlow<UIState<LocationDto>> = _locationState

    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.fetchLocation(id)
        }
    }
}