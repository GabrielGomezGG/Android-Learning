package com.example.mobiletworeview.data.db

import androidx.lifecycle.LiveData
import com.example.mobiletworeview.data.db.entity.PostEntity
import javax.inject.Inject

interface PostDBRepository {

    fun getPostFromDB(): LiveData<List<PostEntity>>

    suspend fun getAllPost(): List<PostEntity>

    suspend fun setPosts(posts: List<PostEntity>)

    suspend fun deletePosts()

    suspend fun deletePost(post: PostEntity)

    suspend fun addPost(post: PostEntity)

}

class PostDBRepositoryImpl @Inject constructor(
    private val postDao: PostDao
) : PostDBRepository {

    override fun getPostFromDB(): LiveData<List<PostEntity>> {
        return postDao.getPost()
    }

    override suspend fun getAllPost(): List<PostEntity> {
        return postDao.getAllPost()
    }

    override suspend fun setPosts(posts: List<PostEntity>) {
        postDao.insertPost(posts)
    }

    override suspend fun deletePosts() {
        postDao.deleteAllPost()
    }

    override suspend fun addPost(post: PostEntity) {
        postDao.addPost(post)
    }

    override suspend fun deletePost(post: PostEntity) {
        postDao.deletePost(post)
    }
}