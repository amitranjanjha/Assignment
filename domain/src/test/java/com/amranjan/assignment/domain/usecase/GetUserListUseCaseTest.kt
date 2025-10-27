package com.amranjan.assignment.domain.usecase

import app.cash.turbine.test
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.domain.repository.UserListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetUserListUseCaseTest {
    @Mock
    private lateinit var userListRepository: UserListRepository
    private lateinit var getUserListUseCase: GetUserListUseCase


    @Before
    fun setUp() {
        getUserListUseCase = GetUserListUseCase(userListRepository)
    }

    @Test
    fun `when repository returns success, use case should emit user list`() = runTest {
        // Arrange
        val mockUsers = listOf(
            User(
                id = 1,
                name = "Amit",
                company = "TechCorp",
                username = "amit_r",
                email = "amit@example.com",
                address = "Bangalore",
                zip = "560001",
                state = "Karnataka",
                country = "India",
                phone = "9876543210",
                photo = "https://example.com/photo.png"
            )
        )

        `when`(userListRepository.getUser()).thenReturn(flowOf(mockUsers))

        getUserListUseCase().test {
            assertEquals(mockUsers, awaitItem())
            awaitComplete()
        }
        verify(userListRepository, times(1)).getUser()
    }

    @Test
    fun `when repository throws error, usecase should propagate exception`() = runTest {
        // Arrange
        val errorMessage = "Something went wrong"
        `when`(userListRepository.getUser()).thenReturn(
            flow { throw IllegalStateException(errorMessage) }
        )

        // Act & Assert

        getUserListUseCase().test {
            val error = awaitError()
            assert(error is IllegalStateException)
            assertEquals(errorMessage, error.message)

        }

        verify(userListRepository, times(1)).getUser()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}