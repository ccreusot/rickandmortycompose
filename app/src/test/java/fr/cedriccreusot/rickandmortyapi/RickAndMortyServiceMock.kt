package fr.cedriccreusot.rickandmortyapi

import fr.cedriccreusot.rickandmortyapi.domain.services.CharacterResponse
import fr.cedriccreusot.rickandmortyapi.domain.services.RickAndMortyService
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import java.io.IOException

object RickAndMortyServiceMock {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .build()
    private val behaviorDelegate: BehaviorDelegate<RickAndMortyService> =
        MockRetrofit.Builder(retrofit).build().create(RickAndMortyService::class.java)

    fun createSerivceThatFail(): RickAndMortyService =
        object : RickAndMortyService {
            override suspend fun getCharacters(): CharacterResponse {
                return behaviorDelegate.returning(Calls.failure<IOException>(IOException()))
                    .getCharacters()
            }
        }

    fun createServiceThatSucceed(
        characterResponse: CharacterResponse? = null
    ): RickAndMortyService =
        object : RickAndMortyService {
            override suspend fun getCharacters(): CharacterResponse {
                return behaviorDelegate.returningResponse(characterResponse).getCharacters()
            }
        }
}