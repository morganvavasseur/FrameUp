package com.example.amaze.models

import com.example.amaze.network.AuthUser

data class ConnectResults(
    val jwt: String,
    val user: AuthUser
)