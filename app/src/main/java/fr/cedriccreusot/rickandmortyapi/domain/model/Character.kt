package fr.cedriccreusot.rickandmortyapi.domain.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
enum class Status {
    @SerialName("Alive") Alive,
    @SerialName("Dead") Dead,
    @SerialName("unknown") Unknown
}

@Serializable
enum class Gender {
    @SerialName("Female") Female,
    @SerialName("Male") Male,
    @SerialName("Genderless") Genderless,
    @SerialName("unknown") Unknown
}

@Serializable
data class Object(val name: String, val url: String)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Object,
    val location: Object,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)