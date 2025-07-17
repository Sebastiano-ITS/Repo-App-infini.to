package com.example.infinito.data.model.user

data class UserModel(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean
)

