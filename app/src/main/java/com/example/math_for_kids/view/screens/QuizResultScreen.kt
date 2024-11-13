package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.QuizPage
import com.example.math_for_kids.viewmodel.QuizViewModel

@Composable
fun QuizResultScreen(navController: NavHostController, viewModel: QuizViewModel) {
    val totalCorrectAnswers by viewModel.totalCorrectAnswers.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Quiz Result:",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            "Level: 1",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            "$totalCorrectAnswers out of 3",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            "correct answers",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text("Congratulations, you have qualified for the next level!")
        Button(onClick = { navController.navigate(QuizPage.QuizTest.route) }) {
            Text("Take Level 1 Quiz Again")
        }
        Button(onClick = { navController.navigate(QuizPage.QuizTest.route) }) {
            Text("Go to the Next Level")
        }
    }
}