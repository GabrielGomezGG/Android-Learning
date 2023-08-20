package com.example.mobiletworeview.di

import com.example.mobiletworeview.data.ApiPostRepository
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.domain.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePostRepository(apiService: ApiService) : PostRepository {
        return ApiPostRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideGetPostUseCase(postRepository: PostRepository, postDBRepository: PostDBRepository) : GetPostUseCase {
        return GetPostUseCase(postRepository, postDBRepository)
    }

}