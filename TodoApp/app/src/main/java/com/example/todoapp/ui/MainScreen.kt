package com.example.todoapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.models.Todo


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(todoViewModel: TodoViewModel) {

    val todos by todoViewModel.todos.observeAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarScreen() },
        bottomBar = {},
    ) {
        when (todos) {
            is TodoUiState.Error -> {
                Column {
                    Text(text = (todos as TodoUiState.Error).message.toString())
                    Text(text = (todos as TodoUiState.Error).codeError.toString())
                }
            }
            TodoUiState.Loading -> CircularProgressIndicator()
            is TodoUiState.Success -> BodyScreen((todos as TodoUiState.Success).todos)
            TodoUiState.NoConnection -> Text(text = "No hay conexion")
            null -> TODO()
        }

    }
}

@Composable
fun TopBarScreen() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
    }
}

@Composable
fun BodyScreen(todos: List<Todo>) {
    LazyColumn() {
        items(todos) {
            Text(text = it.todo)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DisplayMainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarScreen() },
        bottomBar = {},
    ) {

    }
}
