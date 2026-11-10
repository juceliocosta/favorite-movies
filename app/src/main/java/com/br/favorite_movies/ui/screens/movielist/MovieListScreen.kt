package com.br.favorite_movies.ui.screens.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.br.favorite_movies.data.local.MovieEntity
import com.br.favorite_movies.ui.theme.FavoritemoviesTheme

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val search by viewModel.search.collectAsState()
    val movies by viewModel.movies.collectAsState()

    Column() {
        Text("Lista de Filmes", fontSize = 24.sp,
            modifier = Modifier.background(color = Color.LightGray)
                .fillMaxWidth().padding(16.dp)
        )
        Column(modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = search,
                onValueChange = { viewModel.onSearchChanged(it)},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Buscar filme...") },
                trailingIcon = {
                    IconButton(onClick = {}) { Text("\uD83D\uDD0D") } //"🔍"
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            if (movies.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhum filme encontrado.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(movies) { movie ->
                        MovieItemCard(
                            movie = movie,
                            onClick = { onMovieClick(movie.id) }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MovieItemCard(
    movie: MovieEntity,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() },
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(width = 60.dp, height = 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)
            ) {
                Text(text = movie.title)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Ano: ${movie.year}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    FavoritemoviesTheme {
//        MovieListScreen()
    }
}

