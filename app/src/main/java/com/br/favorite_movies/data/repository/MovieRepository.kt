package com.br.favorite_movies.data.repository

import com.br.favorite_movies.data.local.MovieDao
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovies(query: String): List<MovieEntity>
    suspend fun getMovieDetails(id: String): MovieEntity
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun addFavorite(movie: MovieEntity)
    suspend fun removeFavorite(id: String)
}

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val favoriteMovieDao: MovieDao
) : MovieRepository {

    override suspend fun searchMovies(query: String): List<MovieEntity> {
        try {
            val movies = apiService.searchMovies(query)
            return movies
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getMovieDetails(id: String): MovieEntity {
        return apiService.getMovieDetails(id)
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return favoriteMovieDao.getAllFavorites()
    }

    override fun isFavorite(id: String): Flow<Boolean> {
        return favoriteMovieDao.isFavorite(id)
    }

    override suspend fun addFavorite(movie: MovieEntity) {
        favoriteMovieDao.insertFavorite(movie)
    }

    override suspend fun removeFavorite(id: String) {
        favoriteMovieDao.deleteFavoriteById(id)
    }
}
