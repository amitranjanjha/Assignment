package com.amranjan.assignment.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.domain.usecase.GetUserListUseCase
import com.amranjan.assignment.presentation.base.DispatcherProvider
import com.amranjan.assignment.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUserListUseCase: GetUserListUseCase,
                                            private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Initial)
    val uiState: StateFlow<UiState<List<User>>> = _uiState
    public fun fetchUser() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            getUserListUseCase()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}