package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.model.Post

interface ApiRepository {

    suspend fun getPost() : List<Post>

}

class IApiRepository(
    private val apiService: ApiService
) : ApiRepository{
    override suspend fun getPost(): List<Post> {
        return apiService.getPosts().body() ?: emptyList()
    }

}