package br.com.wsilva.starwars.model.dto

import com.google.gson.annotations.SerializedName

data class FilmsDTO (
    @SerializedName( "title") val title: String,
    @SerializedName( "episode_id") val episodeId: Int,
    @SerializedName( "opening_crawl") val opening_crawl: String,
    @SerializedName( "director") val director: String,
    @SerializedName( "producer") val producer: String,
    @SerializedName( "release_date") val release_date: String,
    @SerializedName( "created") val created: String,
    @SerializedName( "edited") val edited: String,
    @SerializedName( "url") val url: String
)