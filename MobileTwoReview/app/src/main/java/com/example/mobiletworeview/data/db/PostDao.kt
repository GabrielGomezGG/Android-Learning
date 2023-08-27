package com.example.mobiletworeview.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiletworeview.data.db.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("select * from post_entity order by id desc")
    fun getPost () : Flow<List<PostEntity>>

    @Query("select * from post_entity")
    suspend fun getAllPost () : List<PostEntity>

    @Query("select * from post_entity where :id = id")
    suspend fun getPostById(id: Int) : PostEntity

    @Delete
    suspend fun deletePost(post : PostEntity)

    @Query("delete from post_entity")
    suspend fun deleteAllPost()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts : List<PostEntity>)

}