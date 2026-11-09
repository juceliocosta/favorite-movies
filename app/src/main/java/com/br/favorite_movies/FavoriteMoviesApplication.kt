package com.br.favorite_movies

import android.app.Application
import com.br.favorite_movies.di.AppContainer
import com.br.favorite_movies.di.DefaultAppContainer

class FavoriteMoviesApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}