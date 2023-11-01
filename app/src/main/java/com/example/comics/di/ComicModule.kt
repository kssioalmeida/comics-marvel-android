package com.example.comics.di

import com.example.comics.data.remote.ComicsService
import com.example.comics.model.ComicsInteractor
import com.example.comics.model.ComicsInteractorImpl
import com.example.comics.model.ComicsMapper
import com.example.comics.model.ComicsRepository
import com.example.comics.model.ComicsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ComicModule {

    @Provides
    fun provideComicsInteractor(repository: ComicsRepository, mapper: ComicsMapper): ComicsInteractor {
        return ComicsInteractorImpl(repository, mapper)
    }

    @Provides
    fun provideComicRepository(comicsService: ComicsService): ComicsRepository {
        return ComicsRepositoryImpl(comicsService)
    }
}