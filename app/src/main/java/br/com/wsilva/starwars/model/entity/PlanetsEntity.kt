package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetsEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rotation_period") val rotation_period: String,
    @ColumnInfo(name = "orbital_period") val orbital_period: String,
    @ColumnInfo(name = "diameter") val diameter: String,
    @ColumnInfo(name = "climate") val climate: String,
    @ColumnInfo(name = "gravity") val gravity: String,
    @ColumnInfo(name = "terrain") val terrain: String,
    @ColumnInfo(name = "surface_water") val surface_water: String,
    @ColumnInfo(name = "population") val population: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "edited") val edited: String,
    @ColumnInfo(name = "url") val url: String
)