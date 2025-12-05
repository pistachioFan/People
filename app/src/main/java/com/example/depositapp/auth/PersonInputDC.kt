package com.example.depositapp.auth

import androidx.annotation.IntegerRes
import kotlinx.serialization.Serializable

@Serializable
data class PersonInputDC(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val birthDate: String,
    val gender: String,
    val groupId: Int
)
