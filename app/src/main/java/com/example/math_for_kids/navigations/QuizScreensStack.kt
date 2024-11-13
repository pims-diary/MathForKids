package com.example.math_for_kids.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.math_for_kids.view.screens.QuizResultScreen
import com.example.math_for_kids.view.screens.quiz.QuizScreen

sealed class QuizPage(val route: String) {
    data object QuizTest : QuizPage("test")
    data object QuizResult : QuizPage("result")
}

@Composable
fun QuizScreensStack() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = QuizPage.QuizTest.route) {
        composable(QuizPage.QuizTest.route) { QuizScreen(navController) }
        composable(QuizPage.QuizResult.route) { QuizResultScreen(navController) }
    }
}