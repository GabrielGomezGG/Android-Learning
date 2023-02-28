package com.example.todoapp.data

import android.util.Log
import com.example.todoapp.data.models.Todo
import com.example.todoapp.ui.TodoUiState
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

interface TodoRepository {
    suspend fun getTodos(): TodoUiState
}

class TodoRepositoryImpl @Inject constructor(
    private val todoService: TodoService
) : TodoRepository {
    override suspend fun getTodos(): TodoUiState {
        return try {
            if (todoService.getTodos().isSuccessful) {
                TodoUiState.Success(todoService.getTodos().body()!!.todos)
            } else {
                TodoUiState.Error(todoService.getTodos().code(),todoService.getTodos().message())
            }
        }catch (e: IOException) {
            TodoUiState.NoConnection
        }
    }
}