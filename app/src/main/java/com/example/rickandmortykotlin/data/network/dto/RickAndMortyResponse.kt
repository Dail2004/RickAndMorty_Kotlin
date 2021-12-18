package com.example.rickandmortykotlin.data.network.dto

import com.google.gson.annotations.SerializedName

class RickAndMortyResponse<T> (
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: MutableList<T>
)