package com.example.freegameapp.fake

import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.repository.GameRepository

class FakeGameRepository : GameRepository {
    override suspend fun getGames(): List<Game> {
        return FakeDataSource.fakeGameListOne
    }
}