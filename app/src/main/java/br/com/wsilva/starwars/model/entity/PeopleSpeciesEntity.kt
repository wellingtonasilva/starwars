package br.com.wsilva.starwars.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "person_species")
class PeopleSpeciesEntity(
    @ColumnInfo(name = "person_id") val personId: Long,
    @ColumnInfo(name = "species_id") val speciesId: Long
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}