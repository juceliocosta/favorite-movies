package com.br.favorite_movies.data.repository

import com.br.favorite_movies.data.local.MovieDao
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.remote.ApiService
import com.br.favorite_movies.data.remote.MovieDetailDto
import com.br.favorite_movies.data.remote.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovies(query: String): Result<List<MovieDto>>
    suspend fun getMovieDetails(id: String): Result<MovieDetailDto>
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun addFavorite(movie: MovieEntity)
    suspend fun removeFavorite(id: String)
}

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val favoriteMovieDao: MovieDao
) : MovieRepository {

    override suspend fun searchMovies(query: String): Result<List<MovieDto>> {
        return try {
            val response = apiService.searchMovies(query)
            if (response.response.equals("True", ignoreCase = true) && response.search != null) {
                Result.success(response.search)
            } else {
                Result.failure(Exception(response.error ?: "Nenhum filme encontrado."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieDetails(id: String): Result<MovieDetailDto> {
        return try {
            val response = apiService.getMovieDetails(id)
            if (response.response.equals("True", ignoreCase = true)) {
                Result.success(response)
            } else {
                Result.failure(Exception(response.error ?: "Detalhes do filme não encontrados."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
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
