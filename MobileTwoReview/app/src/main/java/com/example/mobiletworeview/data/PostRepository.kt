package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.model.Post
import javax.inject.Inject

interface PostRepository {
    suspend fun getPost() : List<Post>
}

class ApiPostRepository @Inject constructor(
    private val apiService: ApiService
) : PostRepository{
    override suspend fun getPost(): List<Post> {
        try {
            val response = apiService.getPosts()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

}