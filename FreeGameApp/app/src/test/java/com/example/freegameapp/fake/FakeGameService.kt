package com.example.freegameapp.fake

import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.network.GameService

class FakeGameService : GameService {
    override suspend fun getAllGames(): List<Game> {
        return FakeDataSource.fakeGameListOne
    }
}