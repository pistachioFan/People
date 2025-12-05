package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginReqDC(
    val login: String,
    val password: String
)
