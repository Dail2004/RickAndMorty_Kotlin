package com.example.rickandmortykotlin.common.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykotlin.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                Resource.Error(
                    data = null, massage = ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }
}