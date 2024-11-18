package com.example.math_for_kids.database.api

import retrofit2.Response
import retrofit2.http.GET

interface NonSqlApiServices {
    @GET("get-player/{player_id}")
    suspend fun getPlayer(): Response<Map<String, Any>>
}