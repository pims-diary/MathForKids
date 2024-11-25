package com.example.math_for_kids.databaselayer.model

data class Question(
    val question: String,
    val answer: Int,
    val options: List<Int>
)
