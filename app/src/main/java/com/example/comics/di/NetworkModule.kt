package com.example.comics.di

import com.example.comics.core.BaseRetrofit
import com.example.comics.data.remote.ComicsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseRetrofit() = BaseRetrofit()

    @Provides
    fun provideComicsService(retrofit: BaseRetrofit): ComicsService {
        return retrofit.builder()
            .build()
            .create(ComicsService::class.java)
    }
}