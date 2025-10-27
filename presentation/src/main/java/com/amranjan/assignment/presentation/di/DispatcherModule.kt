package com.amranjan.assignment.presentation.di

import com.amranjan.assignment.presentation.base.DefaultDispatcherProvider
import com.amranjan.assignment.presentation.base.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {
        @Provides
        @Singleton
        fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
    }
