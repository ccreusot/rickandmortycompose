package fr.cedriccreusot.rickandmortyapi.domain.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.cedriccreusot.rickandmortyapi.domain.model.Character
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

@Serializable
data class Info(val count: Int, val pages: Int, val next: String?, val prev: String?)

@Serializable
data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters() : CharacterResponse

    companion object {
        @ExperimentalSerializationApi
        fun create(): RickAndMortyService {
            val contentType = MediaType.parse("application/json")!!
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(Json.asConverterFactory(contentType))
                .build()
                .create(RickAndMortyService::class.java)
        }
    }
}
