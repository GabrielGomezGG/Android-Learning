package com.example.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.models.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TodoUiState{
    data class Success(val todos : List<Todo>):TodoUiState
    object Loading:TodoUiState
    data class Error(val codeError : Int, val message : String) : TodoUiState
    object NoConnection:TodoUiState
}
@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    private val _todos = MutableLiveData<TodoUiState>(TodoUiState.Loading)
    val todos: LiveData<TodoUiState> = _todos

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _todos.value = todoRepository.getTodos()
        }
    }
}