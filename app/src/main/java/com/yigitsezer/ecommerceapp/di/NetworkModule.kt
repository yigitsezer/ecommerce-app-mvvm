package com.yigitsezer.ecommerceapp.di

import com.google.gson.GsonBuilder
import com.yigitsezer.ecommerceapp.repository.StoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideStoreService(): StoreApiService{
        return Retrofit.Builder()
            .baseUrl("http://alpkaraosmanoglu.com.tr/appcent/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(StoreApiService::class.java)
    }
}