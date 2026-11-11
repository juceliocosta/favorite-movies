package com.br.favorite_movies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies ORDER BY Title ASC")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favorite_movies WHERE imdbID = :id")
    fun getFavoriteById(id: String): Flow<MovieEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE imdbID = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

    @Query("DELETE FROM favorite_movies WHERE imdbID = :imdbID")
    suspend fun deleteFavoriteById(imdbID: String)
}
