package com.example.freegameapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.freegameapp.data.model.Game

@Composable
fun MainScreen(gameViewModel: GameViewModel) {


    val games by gameViewModel.games.observeAsState()

    when(games){
        GameUIState.Error -> ErrorScreen()
        GameUIState.Loading -> LoadingScreen()
        is GameUIState.Success -> GamesListDisplay((games as GameUIState.Success).games)
        else -> {}
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Error de conexion",
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GamesListDisplay(games: List<Game>, modifier : Modifier = Modifier) {

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
    ){

    }
}

@Composable
fun ItemGame(game : Game, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {

    }
}

