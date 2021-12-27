package com.example.rickandmortykotlin.ui.fragment.character.datailCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykotlin.common.resource.Resource
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    private val _characterState = MutableStateFlow<Resource<CharacterDto>>(Resource.Loading())
    val characterState: StateFlow<Resource<CharacterDto>> = _characterState
    fun fetchCharacter(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCharacter(id).collect {
                _characterState.value = it
            }
        }
    }
}