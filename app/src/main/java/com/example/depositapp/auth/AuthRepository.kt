package com.example.depositapp.auth

import android.util.Log

class AuthRepository(val api: AuthApiInterface) {
    suspend fun registerUser(
        userInfo: UserinputDC
    ){
        val response = api.registerUser(userInfo)
        if(response.isSuccessful){
            try{
                val token = response.body()?.token
                if(token.isNullOrEmpty()) throw (Throwable("Empty Token"))
                TokenManager.jwttToken = token
            }catch(e: Exception) {
                Log.d("ARepReg", "No token for you: ${e.message}")
            }
        }
    }

    suspend fun loginUser(
        loginCreds: LoginReqDC
    ){
        val response = api.authUser(loginCreds)
        if(response.isSuccessful){
            try{
                val token = response.body()?.token
                if(token.isNullOrEmpty()) throw (Throwable("Empty Token"))
                TokenManager.jwttToken = token
            }catch(e: Exception) {
                Log.d("ARepLogin", "No token for you: ${e.message}")
            }
        }
    }
    suspend fun getAllUsers(): List<UserDC>?{
        //CHANGE
        return api.getAllUsers().body()
    }
    suspend fun getGroups(): List<GroupDC>?{
        //CHANGE
        return api.getGroups().body()
    }
}