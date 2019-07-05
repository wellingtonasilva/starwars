package br.com.wsilva.starwars.model.dto

data class GenericDTO<T>(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<T>
)