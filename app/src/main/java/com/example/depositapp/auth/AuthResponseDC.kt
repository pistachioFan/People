package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDC(
    val token: String
)
