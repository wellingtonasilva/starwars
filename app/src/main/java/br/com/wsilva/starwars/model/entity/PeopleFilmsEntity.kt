package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_films")
data class PeopleFilmsEntity (
    @ColumnInfo(name = "person_id") val personId: Long,
    @ColumnInfo(name = "films_id") val filmsId: Long
)
{
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}