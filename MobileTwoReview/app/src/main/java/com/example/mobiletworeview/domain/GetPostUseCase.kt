package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.toPost
import com.example.mobiletworeview.data.toPostEntity
import com.example.mobiletworeview.ui.ResponseUiState
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postDBRepository: PostDBRepository
) {
    suspend operator fun invoke(): ResponseUiState {

        val posts = postRepository.getPost()
        val postsDB = postDBRepository.getAllPost()

        if(postsDB.isNullOrEmpty()){
            if (posts.isNullOrEmpty()) {
                return ResponseUiState.Error("Data is empty")
            }else{
                postDBRepository.setPosts(posts.map { it.toPostEntity() })
            }
        }

        return ResponseUiState.Success(postsDB.map { it.toPost() })
    }
}