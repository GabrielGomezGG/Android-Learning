package com.example.todoapp.data

import com.example.todoapp.data.models.Todos
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {

    @GET("todos")
    suspend fun getTodos() : Response<Todos>

}