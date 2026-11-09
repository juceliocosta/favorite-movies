package com.br.favorite_movies.data.remote

data class SearchResponse(
    val search: List<MovieDto>?,
    val totalResults: String?,
    val response: String,
    val error: String?
)

data class MovieDto(
    val title: String,
    val year: String,
    val id: String,
    val type: String?,
    val poster: String?
)

data class MovieDetailDto(
    val title: String,
    val year: String,
    val genre: String?,
    val plot: String?,
    val rating: String?,
    val poster: String?,
    val id: String,
    val response: String,
    val error: String?
)