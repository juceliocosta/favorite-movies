package com.br.favorite_movies.data.repository

import com.br.favorite_movies.data.local.MovieDao
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

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
            val response = apiService.searchMovies(query)
            return response.search?.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    year = dto.year,
                    genre = "",
                    plot = "",
                    rating = "",
                    poster = dto.poster ?: ""
                )
            } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun getMovieDetails(id: String): MovieEntity {
        return try {
            val favoriteMovie = favoriteMovieDao.getFavoriteById(id).firstOrNull()

            if (favoriteMovie != null) {
                return favoriteMovie
            }
            val dto = apiService.getMovieDetails(id)
            MovieEntity(
                id = dto.id,
                title = dto.title,
                year = dto.year,
                genre = dto.genre ?: "N/A",
                plot = dto.plot ?: "Sem sinopse disponível.",
                rating = dto.rating ?: "N/A",
                poster = dto.poster ?: ""
            )
        } catch (e: Exception) {
            e.printStackTrace()
            MovieEntity(
                id = id,
                title = "Erro ao carregar",
                year = "",
                genre = "",
                plot = "",
                rating = "",
                poster = ""
            )
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
