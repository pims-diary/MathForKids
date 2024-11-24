package com.example.math_for_kids.database.api.nonsql

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

data class UpdateLevel(var level: String)

interface NonSqlApiServices {
    @GET("get-player/{player_id}")
    suspend fun getPlayer(
        @Path("player_id") playerId: String
    ): Response<Map<String, Any>>

    @PUT("update-level/{player_id}")
    suspend fun updateLevel(
        @Path("player_id") playerId: String,
        @Body body: UpdateLevel
    ): Response<Map<String, Any>>
}