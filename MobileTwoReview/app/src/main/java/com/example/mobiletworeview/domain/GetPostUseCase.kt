package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.toPostEntity
import com.example.mobiletworeview.ui.ResponseUiState
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke() : ResponseUiState{

        val postsDB = postRepository.getPostFromDB()
        val posts = postRepository.getPost()

        if(postsDB.isNullOrEmpty()){
            if(posts.isNullOrEmpty()){
                return ResponseUiState.Error("Data is empty")
            }
            postRepository.setPostToDatabase(posts!!.map { it.toPostEntity() })
        }

        return ResponseUiState.Success(postsDB)
    }
}