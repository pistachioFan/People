package com.example.depositapp.auth

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApiInterface {
    @GET("/users/{id}")
    suspend fun getUserById(@Path("id")uid: Int): Response<UserDC>
    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserDC>>
    @GET("/users/login/{login}")
    suspend fun getUserByUsername(@Path("login") username: String): Response<UserDC>
    @GET("/users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<UserDC>

    @GET("/groups")
    fun getGroups(): Response<List<GroupDC>>

    @PUT("/users/{id}")
    suspend fun editUser(@Path("id") uid: String, @Body userInfo: UserinputDC): Response<String>

    @POST("/auth/login")
    suspend fun authUser(@Body userCreds: LoginReqDC): Response<AuthResponseDC>
    @POST("/auth/register")
    suspend fun registerUser(@Body userInfo: UserinputDC): Response<AuthResponseDC>

}