package com.example.depositapp.auth

import com.google.firebase.appdistribution.gradle.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


object RetrofitInstance {
    val json = Json{ ignoreUnknownKeys = true}
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()
/*    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://192.168.200.160:8080/api/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }*/

    val apiInterface: AuthApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.200.160:8080/api")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AuthApiInterface::class.java)
    }
}