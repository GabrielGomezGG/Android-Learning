package com.example.mobiletworeview.di

import android.content.Context
import androidx.room.Room
import com.example.mobiletworeview.data.db.AppDataBase
import com.example.mobiletworeview.data.db.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun roomProvide(applicationContext : Context) : AppDataBase {
        return Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun postDaoProvide(appDataBase: AppDataBase) : PostDao{
        return appDataBase.postDao()
    }

}