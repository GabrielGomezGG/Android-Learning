package com.example.freegameapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.freegameapp.data.database.entity.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM games ORDER BY title DESC")
    suspend fun getGames():List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gamesList : List<GameEntity>)

    @Query("delete from games")
    suspend fun deleteAllGames()

}