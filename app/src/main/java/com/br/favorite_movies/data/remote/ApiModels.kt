package com.br.favorite_movies.data.remote

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search") val search: List<MovieDto>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String?
)

data class MovieDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Type") val type: String?,
    @SerializedName("Poster") val poster: String?
)

data class MovieDetailDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Plot") val plot: String?,
    @SerializedName("imdbRating") val rating: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String?
)