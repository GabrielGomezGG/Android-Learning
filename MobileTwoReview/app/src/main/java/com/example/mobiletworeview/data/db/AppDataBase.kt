package com.example.mobiletworeview.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobiletworeview.data.db.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun postDao () : PostDao
}