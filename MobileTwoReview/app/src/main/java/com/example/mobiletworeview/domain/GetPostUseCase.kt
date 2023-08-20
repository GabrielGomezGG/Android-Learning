package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.ui.ResponseUiState
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke() : ResponseUiState{

        val post = postRepository.getPost()

        if(post.isNullOrEmpty()){
            return ResponseUiState.Error("Data is empty")
        }

        return ResponseUiState.Success(post)
    }
}