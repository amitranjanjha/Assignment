
package com.amranjan.assignment.domain.usecase

import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow



import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserListUseCase @Inject constructor(private val userListRepository: UserListRepository) {

    operator fun invoke(): Flow<List<User>> {
        return userListRepository.getUser()
    }

}