package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDC(
    val description: String,
    val time: String,
    //fieldErrors:
)
