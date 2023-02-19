package com.example.freegameapp.data.repository

import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.network.GameService
import javax.inject.Inject

interface GameRepository{
    suspend fun getGames() : List<Game>
}

class GameRepositoryImpl @Inject constructor(
    private val gameService: GameService
) : GameRepository {
    override suspend fun getGames(): List<Game> {
        return gameService.getAllGames().body() ?: emptyList()
    }
}