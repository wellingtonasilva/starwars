package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_planets")
class PeoplePlanetsEntity(
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "planet_id") val planetId: Int
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}