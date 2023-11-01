package com.example.comics.di

import android.content.Context
import com.example.comics.core.ResourceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class CoreModule {

    @Provides
    fun providesResourceBuilder(@ApplicationContext context: Context) = ResourceBuilder(context)
}