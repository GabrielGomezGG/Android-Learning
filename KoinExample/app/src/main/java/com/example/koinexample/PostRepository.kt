package com.example.koinexample

class PostRepository {

    private val posts = listOf(
        Post("Title 1", "Body 1"),
        Post("Title 2", "Body 2"),
        Post("Title 3", "Body 3"),
    )

    fun getPosts(): List<Post>{
        return posts
    }
}