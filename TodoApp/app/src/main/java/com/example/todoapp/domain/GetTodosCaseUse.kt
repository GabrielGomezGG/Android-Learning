package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepositoryImpl
import com.example.todoapp.ui.TodoUiState
import javax.inject.Inject

class GetTodosCaseUse @Inject constructor(
    private val todoRepositoryImpl: TodoRepositoryImpl
) {
    suspend operator fun invoke():TodoUiState{
        return todoRepositoryImpl.getTodos()
    }
}