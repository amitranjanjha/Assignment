package com.amranjan.assignment.presentation.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.presentation.base.ShowError
import com.amranjan.assignment.presentation.base.ShowLoading
import com.amranjan.assignment.presentation.base.UiState
import com.amranjan.assignment.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListRoute(
    onItemClick: (User) -> Unit,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState) {
        if (uiState is UiState.Initial) {
            viewModel.fetchUser()
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, titleContentColor = Color.White
            ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(5.dp)) {
            UserScreen(uiState, onItemClick,viewModel)
        }
    })

}

@Composable
fun UserScreen(uiState: UiState<List<User>>, onItemClick: (User) -> Unit,viewModel: UserListViewModel) {
    when (uiState) {
        is UiState.Success -> {
            UserList(uiState.data, onItemClick)

        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message, { viewModel.fetchUser() })
        }
        is UiState.Initial->{

        }
    }
}

@Composable
fun UserList(users: List<User>, onItemClick: (User) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(users, key = { user -> user.id }) { user ->
            UserRow(user, onItemClick={onItemClick(user)})

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRow(user: User, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onItemClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            ProfileImage(user)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    user.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    user.company,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    user.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        }
    }
}

@Composable
fun ProfileImage(user: User) {
    val imageModifier = Modifier
        .size(60.dp)
        .clip(RoundedCornerShape(4.dp))
    AsyncImage(
        model = user.photo,
        contentDescription = user.name,
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
}
