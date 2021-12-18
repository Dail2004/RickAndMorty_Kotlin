package com.example.rickandmortykotlin.data.network.dto.location

import com.example.rickandmortykotlin.common.base.IBaseDiffModel
import com.example.rickandmortykotlin.data.network.dto.character.OriginDto
import com.example.rickandmortykotlin.data.network.dto.character.SimpleLocationDto
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("residents")
    val residents: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) : IBaseDiffModel