package com.example.mobiletworeview.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.entity.PostEntity

class FakePostDBRepository : PostDBRepository {
    override fun getPostFromDB(): LiveData<List<PostEntity>> {
        return FakeDataSource.fakePostDB
    }

    override suspend fun getAllPost(): List<PostEntity> {
        return FakeDataSource.fakePostDB.value!!
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

class FakePostDBRepositoryFail : PostDBRepository{
    override fun getPostFromDB(): LiveData<List<PostEntity>> {
        return MutableLiveData(emptyList())
    }

    override suspend fun getAllPost(): List<PostEntity> {
        return emptyList()
    }

    override suspend fun setPosts(posts: List<PostEntity>) {
        return
    }

    override suspend fun deletePosts() {
        return
    }

    override suspend fun deletePost(post: PostEntity) {
        return
    }

    override suspend fun addPost(post: PostEntity) {
        return
    }
}