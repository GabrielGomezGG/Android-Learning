package com.example.mobiletworeview.fake

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.Post
import retrofit2.Response

class FakeApiService : ApiService {
    override suspend fun getPosts(): Response<List<Post>> {
        return FakeDataSource.fakeResponseOk
    }
}

class FakeApiServiceFail : ApiService {
    override suspend fun getPosts(): Response<List<Post>> {
        return Response.error(500, null)
    }
}