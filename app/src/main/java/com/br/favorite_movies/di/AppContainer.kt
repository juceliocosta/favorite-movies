package com.br.favorite_movies.di

import android.content.Context
import androidx.room.Room
import com.br.favorite_movies.data.local.AppDatabase
import com.br.favorite_movies.data.local.MovieDao

interface AppContainer {
    val movieDao: MovieDao
}

class DefaultAppContainer(private val context: Context): AppContainer {
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
}