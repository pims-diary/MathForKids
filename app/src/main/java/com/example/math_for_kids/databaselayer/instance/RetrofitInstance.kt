package com.example.math_for_kids.databaselayer.instance

import com.example.math_for_kids.databaselayer.api.nonsql.NonSqlApiServices
import com.example.math_for_kids.databaselayer.api.sql.SqlApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val nonSqlApiServices: NonSqlApiServices by lazy {
        retrofit.create(NonSqlApiServices::class.java)
    }

    val sqlApiServices: SqlApiServices by lazy {
        retrofit.create(SqlApiServices::class.java)
    }
}