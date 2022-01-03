package com.example.rickandmortykotlin.presentation.ui.fragment.character.datailCharacter

import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin.data.repositories.CharacterRepository
import com.example.rickandmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel(
    private val repository: CharacterRepository
) : BaseViewModel() {

    private val _characterState = MutableStateFlow<UIState<CharacterDto>>(UIState.Loading())
    val characterState: StateFlow<UIState<CharacterDto>> = _characterState

    fun fetchCharacter(id: Int) {
        _characterState.subscribeTo {
            repository.fetchCharacter(id)
        }
    }
}