package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserDC(
    val userId: Int,
    val login: String,
    val email: String,
    val phoneNumber: String,
    val roleId: Int,
    val authAllowed: Boolean,
    val personId: Int,
    val createdDate: String,
    val lastLoginDate: String
)
