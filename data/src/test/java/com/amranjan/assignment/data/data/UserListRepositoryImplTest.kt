package com.amranjan.assignment.data.data

import app.cash.turbine.test
import com.amranjan.assignment.data.api.NetworkService
import com.amranjan.assignment.data.repository.UserListRepositoryImpl
import com.amranjan.assignment.domain.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserListRepositoryImplTest {

    @Mock
    private lateinit var networkService: NetworkService
    private lateinit var repository: UserListRepositoryImpl

    @Before
    fun setUp() {
        repository = UserListRepositoryImpl(networkService)
    }

    @Test
    fun getUser_whenNetworkServiceResponseSuccess_shouldReturnSuccess() = runTest {
        // Arrange
        val mockUsers = listOf(
            User(
                id = 1,
                name = "Modesto Abshire",
                company = "Kautzer, Cremin and Yundt",
                username = "Sidney_Satterfield90",
                email = "Asa42@yahoo.com",
                address = "870 Russel Fort",
                zip = "76326-4158",
                state = "Georgia",
                country = "Guadeloupe",
                phone = "1-738-791-2266",
                photo = "https://json-server.dev/ai-profiles/14.png"
            )
        )
        `when`(networkService.getUser()).thenReturn(mockUsers)

        // Act & Assert
        repository.getUser().test {
            val result = awaitItem()
            assertEquals(mockUsers, result)
            awaitComplete()
            cancelAndIgnoreRemainingEvents()
        }

        verify(networkService, times(1)).getUser()
    }

    @Test
    fun getUser_whenNetworkServiceResponseError_shouldReturnError() = runTest {
        // Arrange

        val errorMessage = "Error Message For You"

        doThrow(RuntimeException(errorMessage)).`when`(networkService).getUser()

        repository.getUser().test {


            assertEquals(errorMessage, awaitError().message)
            cancelAndIgnoreRemainingEvents()
        }

        // Act & Assert

        verify(networkService, times(1)).getUser()
    }
}