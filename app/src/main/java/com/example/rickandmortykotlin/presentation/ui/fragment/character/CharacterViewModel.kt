package com.example.rickandmortykotlin.presentation.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin.common.base.BaseViewModel
import com.example.rickandmortykotlin.data.repositories.CharacterRepository

class CharacterViewModel(
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)
}