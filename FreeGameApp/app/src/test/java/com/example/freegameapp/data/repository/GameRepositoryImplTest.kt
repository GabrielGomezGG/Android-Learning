package com.example.freegameapp.data.repository

import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.network.GameService
import com.example.freegameapp.fake.FakeDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit

class GameRepositoryImplTest(){

    private val gameService = mockk<GameService>()
    private val gameRepository = GameRepositoryImpl(gameService = gameService)

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun gameRepository_getGames_returnGameList() = runTest {

        coEvery { gameService.getAllGames() } returns FakeDataSource.fakeGameListOne

        val gamelist = gameRepository.getGames()

        Assert.assertEquals(FakeDataSource.fakeGameListOne, gamelist)
    }

}