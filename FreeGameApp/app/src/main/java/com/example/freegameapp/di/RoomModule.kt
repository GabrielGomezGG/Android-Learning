package com.example.freegameapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.freegameapp.data.database.GameDataBase
import com.example.freegameapp.data.database.dao.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context : Context):GameDataBase{
        return Room.databaseBuilder(
            context,
            GameDataBase::class.java,
            "game_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGameDao(db:GameDataBase):GameDao{
        return db.getGameDao()
    }
    
}