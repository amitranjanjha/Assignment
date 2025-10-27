package com.amranjan.assignment.presentation.userlist

import app.cash.turbine.test
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.domain.usecase.GetUserListUseCase
import com.amranjan.assignment.presentation.base.DispatcherProvider
import com.amranjan.assignment.presentation.base.UiState
import com.amranjan.assignment.presentation.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner



@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {
    @Mock
    private lateinit var getUserListUseCase: GetUserListUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchUser_whenUseCaseReturnsSuccess_shouldSetSuccessUiState() = runTest {
        // Given
        val mockUser = listOf(
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
        doReturn(flowOf(mockUser))
            .`when`(getUserListUseCase)
            .invoke()

        // When
        val viewModel = UserListViewModel(getUserListUseCase,dispatcherProvider)
        viewModel.fetchUser()
        advanceUntilIdle()

        // Then
        viewModel.uiState.test {
            assertEquals(UiState.Success(mockUser), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        verify(getUserListUseCase, times(1)).invoke()
    }
    @Test
    fun fetchUser_whenUseCaseResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<User>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(getUserListUseCase)
                .invoke()

            val viewModel = UserListViewModel(getUserListUseCase, dispatcherProvider)
            viewModel.fetchUser()
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getUserListUseCase, times(1)).invoke()
        }
    }



}