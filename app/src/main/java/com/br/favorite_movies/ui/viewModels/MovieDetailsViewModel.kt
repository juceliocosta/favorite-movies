package com.br.favorite_movies.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository,
    private val id: String
): ViewModel() {
    private val _movie = MutableStateFlow<MovieEntity?>(null)
    val movie: StateFlow<MovieEntity?> = _movie.asStateFlow()

    val isFavorite: StateFlow<Boolean> = repository.isFavorite(id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init { fetchMovie() }

    private fun fetchMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.value = repository.getMovieDetails(id)
        }
    }

    fun toggleFavorite(movieDetail: MovieEntity){
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value){
                repository.removeFavorite(id)
            } else {
                val favoriteEntity = MovieEntity(
                    imdbID = movieDetail.imdbID,
                    Title = movieDetail.Title,
                    Year = movieDetail.Year,
                    Genre = movieDetail.Genre ?: "N/A",
                    Plot = movieDetail.Plot ?: "Sem sinopse disponível.",
                    imdbRating = movieDetail.imdbRating ?: "N/A",
                    Poster = movieDetail.Poster ?: ""
                )
                repository.addFavorite(favoriteEntity)
            }
        }
    }
}