package com.amranjan.assignment.data.api

import com.amranjan.assignment.domain.model.User
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET("users")
    suspend fun getUser(): List<User>

}