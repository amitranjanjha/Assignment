package com.amranjan.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.amranjan.assignment.data.api.NetworkService
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.domain.repository.UserListRepository
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    UserListRepository {

    override fun getUser(): Flow<List<User>> {
        return flow {
            emit(networkService.getUser())
        }.map {
            it
        }
    }



}