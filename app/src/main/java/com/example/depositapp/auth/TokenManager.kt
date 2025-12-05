package com.example.depositapp.auth

object TokenManager {
    var jwttToken: String? = null

    fun haveToken(): Boolean = jwttToken != null
}