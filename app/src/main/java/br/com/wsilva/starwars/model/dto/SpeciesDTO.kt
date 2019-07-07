package br.com.wsilva.starwars.model.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDTO(
    var id: Long = 0,
    @SerializedName( "name") val name: String,
    @SerializedName( "classification") val classification: String,
    @SerializedName( "designation") val designation: String,
    @SerializedName( "average_height") val average_height: String,
    @SerializedName( "skin_colors") val skin_colors: String,
    @SerializedName( "hair_colors") val hair_colors: String,
    @SerializedName( "eye_colors") val eye_colors: String,
    @SerializedName( "average_lifespan") val average_lifespan: String,
    @SerializedName( "homeworld") val homeworld: String,
    @SerializedName( "language") val language: String,
    @SerializedName( "created") val created: String,
    @SerializedName( "edited") val edited: String,
    @SerializedName( "url") val url: String
)