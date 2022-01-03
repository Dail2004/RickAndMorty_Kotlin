package com.example.rickandmortykotlin.servicelocator

import com.example.rickandmortykotlin.data.network.RetrofitClient
import com.example.rickandmortykotlin.data.repositories.CharacterRepository
import com.example.rickandmortykotlin.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin.data.repositories.LocationRepository
import com.example.rickandmortykotlin.presentation.ui.fragment.character.CharacterViewModel
import com.example.rickandmortykotlin.presentation.ui.fragment.character.datailCharacter.CharacterDetailViewModel
import com.example.rickandmortykotlin.presentation.ui.fragment.episode.EpisodeViewModel
import com.example.rickandmortykotlin.presentation.ui.fragment.episode.detailEpisode.EpisodeDetailViewModel
import com.example.rickandmortykotlin.presentation.ui.fragment.location.LocationViewModel
import com.example.rickandmortykotlin.presentation.ui.fragment.location.detailLocation.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodeApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}
val repositoriesModel = module {
    factory { CharacterRepository(get()) }
    factory { EpisodeRepository(get()) }
    factory { LocationRepository(get()) }
}

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
}