package com.example.rickandmortykotlin.data.network.dto.episode

import com.example.rickandmortykotlin.common.base.IBaseDiffModel
import com.example.rickandmortykotlin.data.network.dto.character.OriginDto
import com.example.rickandmortykotlin.data.network.dto.character.SimpleLocationDto
import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    val characters: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) : IBaseDiffModel