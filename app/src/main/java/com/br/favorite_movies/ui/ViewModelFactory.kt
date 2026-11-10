package com.br.favorite_movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.favorite_movies.data.repository.MovieRepository
import com.br.favorite_movies.ui.screens.favorites.FavoritesViewModel
import com.br.favorite_movies.ui.screens.moviedetails.MovieDetailsViewModel
import com.br.favorite_movies.ui.screens.movielist.MovieListViewModel

class AppViewModelFactory(
    private val repository: MovieRepository,
    private val id: String? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieListViewModel::class.java) -> {
                MovieListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> {
                requireNotNull(id) { "id is required for MovieDetailsViewModel" }
                MovieDetailsViewModel(repository, id) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
