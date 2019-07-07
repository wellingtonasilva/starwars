package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_vehicles")
class PeopleVehiclesEntity (
    @ColumnInfo(name = "person_id") val personId: Long,
    @ColumnInfo(name = "vehicles_id") val vehiclesId: Long
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}