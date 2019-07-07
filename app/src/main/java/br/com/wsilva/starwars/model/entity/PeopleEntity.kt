package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PeopleEntity (
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "mass") val mass: String,
    @ColumnInfo(name = "hair_color") val hairColor: String,
    @ColumnInfo(name = "skin_color") val skinColor: String,
    @ColumnInfo(name = "eye_color") val eyeColor: String,
    @ColumnInfo(name = "birth_year") val birthYear: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "homeworld") val homeworld: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "edited") val edited: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "species") var species: String? = null,
    @ColumnInfo(name = "nnvehicles") val numberOfVehicles: Int,
    @ColumnInfo(name = "homeland") var homeland: String? = null
)