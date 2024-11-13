package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.QuizPage

@Composable
fun QuizResultScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Result:")
        Text("Level: 1")
        Text("2 out of 3")
        Text("correct answers")
        Text("Congratulations, you have qualified for the next level!")
        Button(onClick = { navController.navigate(QuizPage.QuizTest.route) }) {
            Text("Take Level 1 Quiz Again")
        }
        Button(onClick = { navController.navigate(QuizPage.QuizTest.route) }) {
            Text("Go to the Next Level")
        }
    }
}