package br.com.wsilva.starwars.model.dto

import com.google.gson.annotations.SerializedName

data class VehiclesDTO(
    @SerializedName( "name") val name: String,
    @SerializedName( "model") val model: String,
    @SerializedName( "manufacturer") val manufacturer: String,
    @SerializedName( "cost_in_credits") val cost_in_credits: String,
    @SerializedName( "length") val length: String,
    @SerializedName( "max_atmosphering_speed") val max_atmosphering_speed: String,
    @SerializedName( "crew") val crew: String,
    @SerializedName( "passengers") val passengers: String,
    @SerializedName( "cargo_capacity") val cargo_capacity: String,
    @SerializedName( "consumables") val consumables: String,
    @SerializedName( "vehicle_class") val vehicle_class: String,
    @SerializedName( "created") val created: String,
    @SerializedName( "edited") val edited: String,
    @SerializedName( "url") val url: String
)