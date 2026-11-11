package com.br.favorite_movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToMovieList: () -> Unit,
    onNavigateToFavorites: () -> Unit
){
    Column() {
        Text("Favorite Movies App", fontSize = 24.sp,
            modifier = Modifier.background(color = Color.LightGray)
                .fillMaxWidth().padding(16.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Column(
            modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Button( onClick = onNavigateToMovieList,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) { Text("Lista de Filmes", fontSize = 16.sp) }

            OutlinedButton( onClick = onNavigateToFavorites,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) { Text("Filmes Favoritos", fontSize = 16.sp) }
        }
    }
}
