package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.toPostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postDBRepository: PostDBRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {


            val posts = postRepository.getPost()

            if(postDBRepository.getAllPost().isEmpty()){
                postDBRepository.setPosts(posts.map { it.toPostEntity() })
            }


//            val posts = postRepository.getPost()
//
//            if(postDBRepository.getAllPost().isEmpty()){
//                postDBRepository.setPosts(posts.map { it.toPostEntity() })
//            }
//
            return postDBRepository.getPostFromDB()


//        val posts = postRepository.getPost()
//
//        if(postDBRepository.getAllPost().isEmpty()){
//            if (posts.isNullOrEmpty()) {
//                return null
//            }else{
////            if(!posts.isNullOrEmpty())
////            if (posts != null) {
//                postDBRepository.setPosts(posts.map { it.toPostEntity() })
//            }
////            }
//        }
//
//        return postDBRepository.getPostFromDB()
    }
}