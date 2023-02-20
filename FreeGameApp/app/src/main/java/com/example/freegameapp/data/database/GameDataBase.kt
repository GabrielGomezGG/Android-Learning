package com.example.freegameapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.freegameapp.data.database.dao.GameDao
import com.example.freegameapp.data.database.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class GameDataBase : RoomDatabase() {

    abstract fun getGameDao():GameDao
}