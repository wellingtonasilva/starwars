package br.com.wsilva.starwars.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "starships")
data class StarshipsEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "manufacturer") val manufacturer: String,
    @ColumnInfo(name = "cost_in_credits") val cost_in_credits: String,
    @ColumnInfo(name = "length") val length: String,
    @ColumnInfo(name = "max_atmosphering_speed") val max_atmosphering_speed: String,
    @ColumnInfo(name = "crew") val crew: String,
    @ColumnInfo(name = "passengers") val passengers: String,
    @ColumnInfo(name = "cargo_capacity") val cargo_capacity: String,
    @ColumnInfo(name = "consumables") val consumables: String,
    @ColumnInfo(name = "hyperdrive_rating") val hyperdrive_rating: String,
    @ColumnInfo(name = "MGLT") val MGLT: String,
    @ColumnInfo(name = "starship_class") val starship_class: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "edited") val edited: String,
    @ColumnInfo(name = "url") val url: String
)