package com.br.favorite_movies.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val favoritesState: StateFlow<List<MovieEntity>> = repository.getFavoriteMovies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            repository.removeFavorite(id)
        }
    }
}