package com.example.rickandmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykotlin.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin.data.network.paginsources.LocationPagingSource
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) {

    fun fetchLocations(): LiveData<PagingData<LocationDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }
}