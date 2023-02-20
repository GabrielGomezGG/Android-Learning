package com.example.freegameapp.data.network

import com.example.freegameapp.data.model.Game
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GameService {
    @GET("games")
    suspend fun getAllGames() : Response<List<Game>>
}