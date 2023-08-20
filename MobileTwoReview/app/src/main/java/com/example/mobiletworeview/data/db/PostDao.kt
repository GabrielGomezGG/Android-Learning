package com.example.mobiletworeview.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiletworeview.data.db.entity.PostEntity

@Dao
interface PostDao {

    @Query("select * from post_entity order by id asc")
    fun getPost () : LiveData<List<PostEntity>>

    @Delete
    suspend fun deletePost(post : PostEntity)

    @Query("delete from post_entity")
    suspend fun deleteAllPost()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts : List<PostEntity>)

}