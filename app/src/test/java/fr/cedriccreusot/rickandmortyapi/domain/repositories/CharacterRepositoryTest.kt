package fr.cedriccreusot.rickandmortyapi.domain.repositories

import fr.cedriccreusot.rickandmortyapi.JsonFileUtils
import fr.cedriccreusot.rickandmortyapi.RickAndMortyServiceMock
import fr.cedriccreusot.rickandmortyapi.domain.model.Character
import fr.cedriccreusot.rickandmortyapi.domain.model.Gender
import fr.cedriccreusot.rickandmortyapi.domain.model.Object
import fr.cedriccreusot.rickandmortyapi.domain.model.Status
import fr.cedriccreusot.rickandmortyapi.domain.services.CharacterResponse
import io.kotest.common.ExperimentalKotest
import io.kotest.common.runBlocking
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@ExperimentalKotest
class CharacterRepositoryTest : ShouldSpec() {
    init {
        testCoroutineDispatcher = true
        coroutineDebugProbes = true

        context("CharacterRepository.getCharacters") {
            should("return an empty list if there is a problem") {
                runBlocking {
                    val repository =
                        CharacterRepository(RickAndMortyServiceMock.createSerivceThatFail())

                    val result = repository.getCharacters()

                    result.isEmpty() shouldBe true
                }
            }

            should("call the service then return a list of characters") {
                val json = JsonFileUtils.readJsonFile("character.json")
                val mocked = Json.decodeFromString<CharacterResponse>(json)
                runBlocking {
                    val repository =
                        CharacterRepository(
                            RickAndMortyServiceMock.createServiceThatSucceed(
                                characterResponse = mocked
                            )
                        )

                    val result = repository.getCharacters()

                    result shouldBe listOf(
                        Character(
                            id = 1,
                            name = "Rick Sanchez",
                            status = Status.Alive,
                            species = "Human",
                            type = "",
                            gender = Gender.Male,
                            origin = Object(
                                name = "Earth (C-137)",
                                url = "https://rickandmortyapi.com/api/location/1"
                            ),
                            location = Object(
                                name = "Citadel of Ricks",
                                url = "https://rickandmortyapi.com/api/location/3",
                            ),
                            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                            episode = listOf(
                                "https://rickandmortyapi.com/api/episode/1",
                                "https://rickandmortyapi.com/api/episode/2",
                            ),
                            url = "https://rickandmortyapi.com/api/character/1",
                            created = "2017-11-04T18:48:46.250Z"
                        ),
                        Character(
                            id = 2,
                            name = "Morty Smith",
                            status = Status.Alive,
                            species = "Human",
                            type = "",
                            gender = Gender.Male,
                            origin = Object(
                                name = "unknown",
                                url = ""
                            ),
                            location = Object(
                                name = "Citadel of Ricks",
                                url = "https://rickandmortyapi.com/api/location/3",
                            ),
                            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                            episode = listOf(
                                "https://rickandmortyapi.com/api/episode/1",
                            ),
                            url = "https://rickandmortyapi.com/api/character/2",
                            created = "2017-11-04T18:50:21.651Z"
                        ),
                    )

                }
            }
        }
    }
}