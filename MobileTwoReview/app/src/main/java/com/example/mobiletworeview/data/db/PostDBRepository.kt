package com.example.mobiletworeview.data.db

import androidx.lifecycle.LiveData
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.db.entity.PostEntity
import com.example.mobiletworeview.data.toPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PostDBRepository {

    fun getPostFromDB(): Flow<List<Post>>

    suspend fun getAllPost(): List<PostEntity>

    suspend fun setPosts(posts: List<PostEntity>)

    suspend fun deletePosts()

    suspend fun deletePost(post: PostEntity)

    suspend fun addPost(post: PostEntity)

}

class PostDBRepositoryImpl @Inject constructor(
    private val postDao: PostDao
) : PostDBRepository {

    override fun getPostFromDB(): Flow<List<Post>> {
        return postDao.getPost().map { items ->  items.map { it.toPost() } }
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