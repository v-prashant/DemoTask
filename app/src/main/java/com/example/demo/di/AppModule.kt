package com.example.demo.di

import com.example.demo.data.ApiService
import com.example.demo.data.repository.UserHoldingRepositoryImpl
import com.example.demo.domain.repository.UserHoldingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserHoldingRepo(apiService: ApiService): UserHoldingRepository {
        return UserHoldingRepositoryImpl(apiService)
    }

}