package com.example.math_for_kids.database.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NonSqlApiServices {
    @GET("get-player/{player_id}")
    suspend fun getPlayer(@Path("player_id") playerId: String): Response<Map<String, Any>>
}