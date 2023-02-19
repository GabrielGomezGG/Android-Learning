package com.example.freegameapp.di

import com.example.freegameapp.data.network.GameService
import com.example.freegameapp.data.repository.GameRepository
import com.example.freegameapp.data.repository.GameRepositoryImpl
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
                .baseUrl("https://www.freetogame.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideGameService(retrofit : Retrofit):GameService{
        return retrofit.create(GameService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(gameService : GameService):GameRepository{
        return GameRepositoryImpl(gameService)
    }
    
}