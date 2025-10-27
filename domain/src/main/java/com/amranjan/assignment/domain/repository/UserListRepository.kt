
package com.amranjan.assignment.domain.repository

import com.amranjan.assignment.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserListRepository {

    fun getUser(): Flow<List<User>>

}