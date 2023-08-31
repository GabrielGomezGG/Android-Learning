package com.example.mobiletworeview.domain

import android.util.Log
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.api.model.PostResponse
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.toPostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postDBRepository: PostDBRepository
) {


    suspend operator fun invoke(): Flow<List<Post>> {

        var posts = emptyList<PostResponse>()

        val flowPost = flow{
            if (postDBRepository.getAllPost().isEmpty()) {
                posts = postRepository.getPost() ?: emptyList()
                postDBRepository.setPosts(posts.map { it.toPostEntity() })
            }
            emit(posts)
        }


        val postsDB = postDBRepository.getPostFromDB().combine(flowPost){
            postDB, post -> postDB
        }

        return postsDB
    }
}