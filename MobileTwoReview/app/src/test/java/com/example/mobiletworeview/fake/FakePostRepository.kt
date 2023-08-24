package com.example.mobiletworeview.fake

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.api.model.PostResponse

class FakePostRepository : PostRepository {
    override suspend fun getPost(): List<PostResponse> {
        return FakeDataSource.fakeResponseOk.body()!!
    }
}

class FakePostRepositoryFail : PostRepository{
    override suspend fun getPost(): List<PostResponse> {
        return emptyList()
    }
}