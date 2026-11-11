package com.br.favorite_movies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("Genre")
    val Genre: String? = null,
    @SerializedName("Plot")
    val Plot: String? = null,
    @SerializedName("imdbRating")
    val imdbRating: String? = null,
    @SerializedName("Poster")
    val Poster: String? = null
)
