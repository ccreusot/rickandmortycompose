package fr.cedriccreusot.rickandmortyapi.domain.repositories

import fr.cedriccreusot.rickandmortyapi.domain.model.Character;
import fr.cedriccreusot.rickandmortyapi.domain.services.RickAndMortyService

class CharacterRepository(private val service: RickAndMortyService) {
    suspend fun getCharacters(): List<Character> {
        return try {
            val result = service.getCharacters()
            result.results
        } catch (exception: Exception) {
            emptyList()
        }
    }

}
