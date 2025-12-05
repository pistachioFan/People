package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserinputDC(
    val login: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val roleId: Int,
    val authAllowed: Boolean,
    val personId: Int
)