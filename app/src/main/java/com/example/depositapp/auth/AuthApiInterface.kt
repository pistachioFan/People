package com.example.depositapp.auth

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApiInterface {
    @GET("/users/{id}")
    fun getUserById(@Path("id")uid: Int): String
    @GET("/users")
    fun getAllUsers(): String
    @GET("/users/login/{login}")
    fun getUserByUsername(@Path("login") username: String): String
    @GET("/users/email/{email}")
    fun getUserByEmail(@Path("email") email: String): String


    @POST("/auth/login")
    //CHANGE CHANGE CHANGE CHANGE
    fun authUser(@Body userCreds: Unit): String

    @POST("/auth/register")
    //CHANGE CHANGE CHANGE CHANGE
    fun registerUser(@Body userCreds: Unit): String

    @GET("/groups")
    fun getGroups(): String
}