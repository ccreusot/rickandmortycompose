package fr.cedriccreusot.rickandmortyapi.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.rickandmortyapi.domain.repositories.CharacterRepository
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

}