package com.br.favorite_movies.data.remote

import com.br.favorite_movies.data.local.MovieEntity
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search")
    val search: List<MovieEntity>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Error")
    val error: String?
)
