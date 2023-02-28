package com.example.todoapp.data.models

data class Todos(
    val limit: Int,
    val skip: Int,
    val todos: List<Todo>,
    val total: Int
)