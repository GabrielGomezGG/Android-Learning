package com.example.freegameapp.data.repository

import com.example.freegameapp.data.database.dao.GameDao
import com.example.freegameapp.data.database.entity.GameEntity
import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.network.GameService
import javax.inject.Inject

interface GameRepository{
    suspend fun getGamesApi() : List<Game>
    suspend fun getGamesDB() : List<Game>
    suspend fun insertGames(games : List<GameEntity>)

    suspend fun clearGames()
}

class GameRepositoryImpl @Inject constructor(
    private val gameService: GameService,
    private val gameDao: GameDao
) : GameRepository {
    override suspend fun getGamesApi(): List<Game> {
        return gameService.getAllGames().body() ?: emptyList()
    }

    override suspend fun getGamesDB(): List<Game> {
        return gameDao.getGames().map { Game(
            it.developer, it.gameUrl, it.genre,
            it.id, it.platform,it.publisher,
            it.releaseDate, it.shortDescription, it.thumbnail, it.title
        )}
    }

    override suspend fun insertGames(games: List<GameEntity>) {
        gameDao.insertAll(games)
    }

    override suspend fun clearGames() {
        gameDao.deleteAllGames()
    }
}