package com.example.mobiletworeview.data.api

import com.example.mobiletworeview.data.api.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}