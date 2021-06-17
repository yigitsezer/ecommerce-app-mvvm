package com.yigitsezer.ecommerceapp.di

import android.content.Context
import com.yigitsezer.ecommerceapp.EcommerceApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): EcommerceApplication {
        return app as EcommerceApplication
    }
}