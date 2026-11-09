package com.br.favorite_movies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey
    val imdbId: String,
    val title: String,
    val year: String,
    val genre: String,
    val plot: String,
    val rating: String,
    val poster: String
)