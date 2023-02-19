package com.example.freegameapp.ui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.freegameapp.data.model.Game

@Composable
fun MainScreen(gameViewModel: GameViewModel) {

    val games by gameViewModel.games.observeAsState()

    when(games){
        GameUIState.Error -> Text(text = "error")
        GameUIState.Loading -> CircularProgressIndicator()
        is GameUIState.Success -> GamesListDisplay((games as GameUIState.Success).games)
        null -> TODO()
    }
}

@Composable
fun GamesListDisplay(games: List<Game>) {
    Text(text = games[0].title)
}