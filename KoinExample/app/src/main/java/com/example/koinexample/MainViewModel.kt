package com.example.koinexample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _post = MutableStateFlow<List<Post>>(emptyList())
    val post : Flow<List<Post>> = _post.asStateFlow()

    init {
        _post.value = postRepository.getPosts()
    }

}