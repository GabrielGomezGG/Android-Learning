package com.example.mobiletworeview.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.entity.PostEntity
import kotlinx.coroutines.flow.Flow

class FakePostDBRepository : PostDBRepository {
    override fun getPostFromDB(): Flow<List<Post>> {
        return FakeDataSource.fakePostDB
    }

    override suspend fun getAllPost(): List<PostEntity> {
        return FakeDataSource.fakeAllPost
    }

    override suspend fun setPosts(posts: List<PostEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePosts() {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun addPost(post: PostEntity) {
        TODO("Not yet implemented")
    }
}