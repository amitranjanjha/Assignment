package com.amranjan.assignment.presentation.base

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amranjan.assignment.domain.model.User
import com.amranjan.assignment.presentation.userdetail.UserDetailsRoute
import com.amranjan.assignment.presentation.userlist.UserListRoute
import com.google.gson.Gson

sealed class Route(val name: String) {
    object UserList : Route("userlist")
    object UserDetails : Route("userDetails")
}

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Route.UserList.name
    ) {
        composable(route = Route.UserList.name) {
            UserListRoute(
                onItemClick = {user->
                    val json = Uri.encode(Gson().toJson(user))
                    navController.navigate("${Route.UserDetails.name}/$json")


                }
            )
        }
        composable(route = "${Route.UserDetails.name}/{userJson}")
        {
            backStackEntry ->
            val source = backStackEntry.arguments?.getString("userJson")
            val user = Gson().fromJson(source, User::class.java)
            UserDetailsRoute(
                onBackPressed={
                    navController.popBackStack()
                },
                user
            )
        }

    }


}