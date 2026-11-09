package com.br.favorite_movies.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = API_KEY
    ): SearchResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDto

    companion object {
        const val API_KEY = "ff6cb10f"
    }
}