package com.example.math_for_kids.database.api.sql

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginCreds(
    @SerializedName("Email") var email: String,
    @SerializedName("Password") var password: String
)

interface SqlApiServices {
    @POST("sqlite/login")
    suspend fun login(
        @Body body: LoginCreds
    ): Response<Map<String, Any>>
}