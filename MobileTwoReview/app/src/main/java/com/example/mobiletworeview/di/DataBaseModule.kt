package com.example.mobiletworeview.di

import android.content.Context
import androidx.room.Room
import com.example.mobiletworeview.data.db.AppDataBase
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.PostDBRepositoryImpl
import com.example.mobiletworeview.data.db.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun roomProvide(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "app_database"
        )
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun postDaoProvide(appDataBase: AppDataBase): PostDao {
        return appDataBase.postDao()
    }

    @Singleton
    @Provides
    fun postDBRepositoryProvide(postDao: PostDao): PostDBRepository {
        return PostDBRepositoryImpl(postDao)
    }

}