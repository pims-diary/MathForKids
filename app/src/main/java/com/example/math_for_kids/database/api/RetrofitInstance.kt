package com.example.math_for_kids.database.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NonSqlApiServices by lazy {
        retrofit.create(NonSqlApiServices::class.java)
    }
}