package com.example.infinito.data.model

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean
)

