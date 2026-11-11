package com.br.favorite_movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.br.favorite_movies.FavoriteMoviesApplication
import com.br.favorite_movies.ui.AppViewModelFactory
import com.br.favorite_movies.ui.screens.FavoritesScreen
import com.br.favorite_movies.ui.viewModels.FavoritesViewModel
import com.br.favorite_movies.ui.screens.HomeScreen
import com.br.favorite_movies.ui.screens.MovieDetailsScreen
import com.br.favorite_movies.ui.viewModels.MovieDetailsViewModel
import com.br.favorite_movies.ui.screens.MovieListScreen
import com.br.favorite_movies.ui.viewModels.MovieListViewModel

object NavRoutes {
    const val HOME = "home"
    const val MOVIE_LIST = "movie_list"
    const val MOVIE_DETAILS = "movie_details/{id}"
    const val FAVORITES = "favorites"

    fun createMovieDetailsRoute(id: String): String = "movie_details/$id"
}

@Composable
fun MoviesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current.applicationContext
    val appContainer = (context as FavoriteMoviesApplication).container

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HOME
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                onNavigateToMovieList = { navController.navigate(NavRoutes.MOVIE_LIST) },
                onNavigateToFavorites = { navController.navigate(NavRoutes.FAVORITES) }
            )
        }

        composable(NavRoutes.MOVIE_LIST) {
            val movieListViewModel: MovieListViewModel = viewModel(
                factory = AppViewModelFactory(appContainer.movieRepository)
            )
            MovieListScreen(
                viewModel = movieListViewModel,
                onMovieClick = { id ->
                    navController.navigate(NavRoutes.createMovieDetailsRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavRoutes.MOVIE_DETAILS,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val movieDetailsViewModel: MovieDetailsViewModel = viewModel(
                factory = AppViewModelFactory(appContainer.movieRepository, id = id)
            )
            MovieDetailsScreen(
                viewModel = movieDetailsViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.FAVORITES) {
            val favoritesViewModel: FavoritesViewModel = viewModel(
                factory = AppViewModelFactory(appContainer.movieRepository)
            )
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onMovieClick = { id ->
                    navController.navigate(NavRoutes.createMovieDetailsRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
