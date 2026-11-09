package com.br.favorite_movies.di

import android.content.Context
import androidx.room.Room
import com.br.favorite_movies.data.local.AppDatabase
import com.br.favorite_movies.data.local.MovieDao
import com.br.favorite_movies.data.remote.ApiService
import com.br.favorite_movies.data.repository.MovieRepository
import com.br.favorite_movies.data.repository.MovieRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val movieDao: MovieDao
    val apiService: ApiService
    val movieRepository: MovieRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    private val BASE_URL = "https://www.omdbapi.com/"
    override val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "favorite_movies_db"
        ).build()
    }

    override val movieDao: MovieDao by lazy {
        database.movieDao()
    }

    override val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(
            apiService = apiService,
            favoriteMovieDao = movieDao
        )
    }
}