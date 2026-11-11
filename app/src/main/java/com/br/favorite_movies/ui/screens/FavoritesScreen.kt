package com.br.favorite_movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.ui.viewModels.FavoritesViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onMovieClick: (String) -> Unit,
    onNavigateBack: () -> Unit
){
    val favorites by viewModel.favoritesState.collectAsState()

    Column() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = Color.LightGray)
                .fillMaxWidth().padding(16.dp)
        ) {
            IconButton(onClick = onNavigateBack) { Text("⬅\uFE0F") }
            Text("Filmes Favoritos", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Column(
            modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = favorites,
                    key = { it.imdbID }
                ) { movie ->
                    FavoriteMovieItemCard(
                        movie = movie,
                        onClick = { onMovieClick(movie.imdbID) },
                        onDeleteClick = { viewModel.removeFavorite(movie.imdbID) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteMovieItemCard(
    movie: MovieEntity,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = movie.Poster,
                contentDescription = movie.Title,
                modifier = Modifier.size(width = 60.dp, height = 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)
            ) {
                Text(text = movie.Title)

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Ano: ${movie.Year}")

                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Gênero: ${movie.Genre ?: "N/A"}")
            }

            IconButton(onClick = onDeleteClick) {
                Text("\uD83D\uDDD1\uFE0F") //"🗑️"
            }
        }
    }
}
