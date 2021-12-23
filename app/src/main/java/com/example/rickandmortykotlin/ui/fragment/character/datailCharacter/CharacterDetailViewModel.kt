package com.example.rickandmortykotlin.ui.fragment.character.datailCharacter

import androidx.lifecycle.ViewModel
import com.example.rickandmortykotlin.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {
    fun fetchCharacter(id: Int) = repository.fetchCharacter(id)
}