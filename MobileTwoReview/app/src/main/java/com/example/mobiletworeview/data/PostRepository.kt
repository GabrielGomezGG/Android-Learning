package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.PostResponse
import javax.inject.Inject

interface PostRepository {
    suspend fun getPost(): List<PostResponse>

}

class ApiPostRepository @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override suspend fun getPost(): List<PostResponse> {
        return apiService.getPosts().body()!!

    }


}