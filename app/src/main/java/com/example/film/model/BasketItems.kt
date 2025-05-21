package com.example.film.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "basket")
data class BasketItems(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val originalLanguage: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val popularity: Double?
)