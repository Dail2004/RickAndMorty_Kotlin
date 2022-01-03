package com.example.rickandmortykotlin.data.network.paginsources

import com.example.rickandmortykotlin.common.base.BasePagingSource
import com.example.rickandmortykotlin.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationDto>({ position ->
    service.fetchLocations(position)
})