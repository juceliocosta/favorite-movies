package com.br.favorite_movies.ui.screens.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.favorite_movies.ui.theme.FavoritemoviesTheme

@Composable
fun MovieListScreen(
) {
    Column() {
        Text("Lista de Filmes", fontSize = 24.sp,
            modifier = Modifier.background(color = Color.LightGray)
                .fillMaxWidth().padding(16.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Buscar filme...") },
                trailingIcon = {
                    IconButton(onClick = {}) { Text("\uD83D\uDD0D") }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    FavoritemoviesTheme {
        MovieListScreen()
    }
}

