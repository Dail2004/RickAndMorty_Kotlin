package com.example.rickandmortykotlin.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykotlin.common.base.BaseRepository
import com.example.rickandmortykotlin.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin.data.network.paginsources.LocationPagingSource
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val service: LocationApiService,
) : BaseRepository() {

    fun fetchLocations(): Flow<PagingData<LocationDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }
}