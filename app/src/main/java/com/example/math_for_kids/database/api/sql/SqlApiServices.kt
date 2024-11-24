package com.example.math_for_kids.database.api.sql

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginCreds(
    @SerializedName("Email") var email: String,
    @SerializedName("Password") var password: String
)

data class RegisterCreds(
    @SerializedName("Email") var email: String,
    @SerializedName("Password") var password: String,
    @SerializedName("Name") var name: String
)

interface SqlApiServices {
    @POST("sqlite/login")
    suspend fun login(
        @Body body: LoginCreds
    ): Response<Map<String, Any>>

    @POST("sqlite/register")
    suspend fun register(
        @Body body: RegisterCreds
    ): Response<Map<String, Any>>
}