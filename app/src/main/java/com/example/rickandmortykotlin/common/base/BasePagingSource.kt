package com.example.rickandmortykotlin.common.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortykotlin.data.network.dto.RickAndMortyResponse
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<T : Any>(
    private val request: suspend (position: Int) -> RickAndMortyResponse<T>,
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val position = params.key ?: 1

        return try {
            val response = request(position)
            val next = response.info.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}