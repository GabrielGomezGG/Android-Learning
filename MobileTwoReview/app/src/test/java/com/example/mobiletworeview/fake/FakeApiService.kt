package com.example.mobiletworeview.fake

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.PostResponse
import retrofit2.Response

class FakeApiService : ApiService {
    override suspend fun getPosts(): Response<List<PostResponse>> {
        return FakeDataSource.fakeResponseOk
    }
}

class FakeApiServiceFail : ApiService {
    override suspend fun getPosts(): Response<List<PostResponse>> {
        return Response.error(500, null)
    }
}