package com.example.mobiletworeview.fake

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.model.Post

class FakePostRepository : PostRepository {
    override suspend fun getPost(): List<Post>? {
        return FakeDataSource.fakeResponseOk.body()
    }
}

class FakePostRepositoryFail : PostRepository{
    override suspend fun getPost(): List<Post>? {
        return null
    }
}