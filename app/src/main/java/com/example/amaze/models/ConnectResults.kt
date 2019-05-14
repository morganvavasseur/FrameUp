package com.example.amaze.models

import com.example.amaze.network.UserResult

data class ConnectResults(
    val jwt: String,
    val user: User
)