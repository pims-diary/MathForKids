package com.example.math_for_kids.database.api.sql

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginCreds(var email: String, var password: String)

interface SqlApiServices {
    @POST
    suspend fun login(
        @Body body: LoginCreds
    ): Response<Map<String, Any>>
}