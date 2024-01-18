package com.example.camaraintentexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageEntity")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Insert
    suspend fun insertImage(imageEntity: ImageEntity)

}