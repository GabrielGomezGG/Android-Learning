package com.example.freegameapp.domain

import android.util.Log
import com.example.freegameapp.data.database.entity.GameEntity
import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.repository.GameRepository
import javax.inject.Inject

class GetAllGamesUserCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): List<Game> {

        val gameListDB = gameRepository.getGamesDB()
        return if (gameListDB.isEmpty()) {
            val gameList = gameRepository.getGamesApi()
            gameRepository.insertGames(gameList.map {
                GameEntity(
                    it.id, it.developer, it.gameUrl,
                    it.genre, it.platform, it.publisher,
                    it.releaseDate, it.shortDescription, it.thumbnail, it.title
                )
            })
            gameRepository.updateGame(405, "2014-03-01")
            gameRepository.getGamesDB()
        } else {
            gameRepository.getGamesDB()
        }
    }
}