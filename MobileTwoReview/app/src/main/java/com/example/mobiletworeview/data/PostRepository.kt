package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.Post
import com.example.mobiletworeview.data.db.PostDao
import com.example.mobiletworeview.data.db.entity.PostEntity
import javax.inject.Inject

interface PostRepository {
    suspend fun getPost() : List<Post>?

    suspend fun getPostFromDB() : List<Post>

    suspend fun setPostToDatabase(posts : List<PostEntity>)
}

class ApiPostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) : PostRepository{
    override suspend fun getPost(): List<Post>? {
        return try {
            return apiService.getPosts().body()!!
        } catch (e: Exception) {
            return null
        }

    }

    override suspend fun getPostFromDB(): List<Post> {
        var post : List<PostEntity> = emptyList()
        postDao.getPost().collect{
            post = it
        }
        return post.map { it.toPost() }
    }

    override suspend fun setPostToDatabase(posts: List<PostEntity>) {
        postDao.setPosts(posts)
    }

}