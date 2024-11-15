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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.QuizPage
import com.example.math_for_kids.storage.updateLevel
import com.example.math_for_kids.viewmodel.QuizViewModel
import kotlinx.coroutines.launch

@Composable
fun QuizResultScreen(navController: NavHostController, viewModel: QuizViewModel) {
    val totalCorrectAnswers by viewModel.totalCorrectAnswers.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val level by viewModel.level.collectAsState()

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

        val isLevelComplete = viewModel.totalQuestions / 2 < totalCorrectAnswers
        if (isLevelComplete) {
            Text("Congratulations, you have qualified for the next level!")
        } else {
            Text("Oops, you did not qualify for the next level unfortunately.")
        }
        Button(onClick = { navController.navigate(QuizPage.QuizTest.route) }) {
            Text("Take Level $level Quiz Again")
        }
        if (isLevelComplete) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.changeLevel()
                        updateLevel(context = context, level = level)
                        navController.navigate(QuizPage.QuizTest.route)
                    }
                }
            ) {
                Text("Go to the Next Level")
            }
        }
    }
}