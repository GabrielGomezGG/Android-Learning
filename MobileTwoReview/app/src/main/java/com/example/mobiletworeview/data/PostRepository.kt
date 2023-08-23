package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.Post
import com.example.mobiletworeview.data.db.PostDao
import com.example.mobiletworeview.data.db.entity.PostEntity
import javax.inject.Inject

interface PostRepository {
    suspend fun getPost(): List<Post>

}

class ApiPostRepository @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override suspend fun getPost(): List<Post> {
        return apiService.getPosts().body()!!

    }


}