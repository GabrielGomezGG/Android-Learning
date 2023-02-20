package com.example.freegameapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val developer: String,
    @ColumnInfo(name = "game_url") val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "short_description") val shortDescription: String,
    val thumbnail: String,
    val title: String
)
