package com.amranjan.assignment.domain.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("address")
    val address: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("zip")
    val zip: String
)