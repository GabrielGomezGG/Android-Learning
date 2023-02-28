package com.example.todoapp.di

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.TodoRepositoryImpl
import com.example.todoapp.data.TodoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoService(retrofit : Retrofit): TodoService {
        return retrofit.create(TodoService::class.java)
    }

    @Singleton
    @Provides
    fun provideTodoRepository(todoService : TodoService):TodoRepository{
        return TodoRepositoryImpl(todoService)
    }

}