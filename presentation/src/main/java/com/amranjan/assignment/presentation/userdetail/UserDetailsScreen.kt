package com.amranjan.assignment.presentation.userdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.presentation.base.UserInfoText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsRoute(
    onBackPressed:(()->Unit)? = null,
    user: User
) {

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ), title = { Text(text = "User Details") },
            navigationIcon = {
                if (onBackPressed != null) {
                    IconButton(onClick = onBackPressed) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                        )
                    }
                } else {

                }
            }
            )
    }, content = { padding ->
       Column(modifier = Modifier.padding(padding)) {
            UserDetailsScreen(user)
       }
    })

}

@Composable
fun UserDetailsScreen(user:User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        AsyncImage(
            model = user.photo,
            contentDescription = user.name,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        UserInfoText(
            text = user.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        UserInfoText(text = user.email)
        UserInfoText(text = user.phone)
        UserInfoText(text = user.company)
    }



}


