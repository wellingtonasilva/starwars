package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "classification") val classification: String,
    @ColumnInfo(name = "designation") val designation: String,
    @ColumnInfo(name = "average_height") val average_height: String,
    @ColumnInfo(name = "skin_colors") val skin_colors: String,
    @ColumnInfo(name = "hair_colors") val hair_colors: String,
    @ColumnInfo(name = "eye_colors") val eye_colors: String,
    @ColumnInfo(name = "average_lifespan") val average_lifespan: String,
    @ColumnInfo(name = "homeworld") val homeworld: String,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "edited") val edited: String,
    @ColumnInfo(name = "url") val url: String
)