package com.br.favorite_movies.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _search = MutableStateFlow<String>("Batman")
    val search: StateFlow<String> = _search.asStateFlow()

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies: StateFlow<List<MovieEntity>> = _movies.asStateFlow()

    init { searchMovies(_search.value)}

    fun onSearchChanged(newSearch: String) {
        _search.value = newSearch
    }

    fun executeSearch(){
        searchMovies(_search.value)
    }

    fun searchMovies(search: String){
        if (search.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            _movies.value = repository.searchMovies((search.trim()))
        }
    }
}