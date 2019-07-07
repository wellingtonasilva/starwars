package br.com.wsilva.starwars.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmsEntity (
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "episode_id") val episodeId: Int,
    @ColumnInfo(name = "opening_crawl") val opening_crawl: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "producer") val producer: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "edited") val edited: String,
    @ColumnInfo(name = "url") val url: String
)