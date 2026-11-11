package com.br.favorite_movies.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.br.favorite_movies.data.local.MovieDao
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

interface MovieRepository {
    suspend fun searchMovies(query: String): List<MovieEntity>
    suspend fun getMovieDetails(id: String): MovieEntity?
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
        return try {
            val response = apiService.searchMovies(query)
            response.search ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "Erro na rede ao buscar os filmes", e)
            emptyList()
        }
    }

    override suspend fun getMovieDetails(id: String): MovieEntity? {
        return try {
            val favoriteMovie = favoriteMovieDao.getFavoriteById(id).firstOrNull()
            if (favoriteMovie != null) {
                return favoriteMovie
            }
            apiService.getMovieDetails(id)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao buscar detalhes do filme", e)
            null
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
