package br.com.wsilva.starwars.model.dto

import com.google.gson.annotations.SerializedName

data class PeopleDTO(
    var id: Long = 0,
    @SerializedName("name") val name: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("height") val height: String,
    @SerializedName("homeworld") val homeWorldUrl: String,
    @SerializedName("mass") val mass: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("created") val created: String,
    @SerializedName("edited") val edited: String,
    @SerializedName("url") val url: String,
    @SerializedName("films") val films: List<String>,
    @SerializedName("species") val species: List<String>,
    @SerializedName("starships") val starships: List<String>,
    @SerializedName("vehicles") val vehicles: List<String>
)