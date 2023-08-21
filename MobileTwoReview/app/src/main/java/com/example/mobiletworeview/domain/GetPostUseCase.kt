package com.example.mobiletworeview.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.api.model.Post
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.entity.PostEntity
import com.example.mobiletworeview.data.toPost
import com.example.mobiletworeview.data.toPostEntity
import com.example.mobiletworeview.ui.ResponseUiState
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postDBRepository: PostDBRepository
) {
    suspend operator fun invoke(): LiveData<List<PostEntity>>? {

        val posts = postRepository.getPost()

        if(postDBRepository.getAllPost().isEmpty()){
            if (posts.isNullOrEmpty()) {
                return null
            }else{
                postDBRepository.setPosts(posts.map { it.toPostEntity() })
            }
        }

        return postDBRepository.getPostFromDB()
    }
}