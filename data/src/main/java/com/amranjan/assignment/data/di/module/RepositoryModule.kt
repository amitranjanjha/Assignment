package com.amranjan.assignment.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.amranjan.assignment.data.api.NetworkService
import com.amranjan.assignment.data.repository.UserListRepositoryImpl
import com.amranjan.assignment.domain.repository.UserListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserListRepository(networkService: NetworkService): UserListRepository {
        return UserListRepositoryImpl(networkService)
    }

}