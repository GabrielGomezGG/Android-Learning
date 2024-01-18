package com.example.camaraintentexample.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun roomProvide(@ApplicationContext applicationContext: Context) : ImageDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun imageDaoProvide(appDataBase: ImageDatabase): ImageDao {
        return appDataBase.imageDao()
    }

}