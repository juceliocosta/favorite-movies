package com.br.favorite_movies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Genre: String,
    val Plot: String,
    val imdbRating: String,
    val Poster: String
)