package com.example.mobiletworeview.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiletworeview.data.db.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("select * from post_entity order by id asc")
    fun getPost () : Flow<List<PostEntity>>

    @Delete
    suspend fun deletePost(post : PostEntity)

    @Query("delete from post_entity")
    suspend fun deleteAllPost()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: PostEntity)

    @Insert
    suspend fun setPosts(posts : List<PostEntity>)

}