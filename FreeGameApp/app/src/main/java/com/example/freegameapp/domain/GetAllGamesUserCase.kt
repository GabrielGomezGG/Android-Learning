package com.example.freegameapp.domain

import com.example.freegameapp.data.database.entity.GameEntity
import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.repository.GameRepository
import javax.inject.Inject

class GetAllGamesUserCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): List<Game> {
        val gameList = gameRepository.getGamesApi()
        if (gameList.isNotEmpty()) {
            gameRepository.clearGames()
            gameRepository.insertGames(gameList.map {
                GameEntity(
                    it.id, it.developer, it.gameUrl,
                    it.genre, it.platform, it.publisher,
                    it.releaseDate, it.shortDescription, it.thumbnail, it.title
                )
            })
            return gameList
        }
        return gameRepository.getGamesDB()
    }
}