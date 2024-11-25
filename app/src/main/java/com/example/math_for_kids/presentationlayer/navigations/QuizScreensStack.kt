package com.example.math_for_kids.presentationlayer.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.math_for_kids.presentationlayer.view.screens.quiz.QuizResultScreen
import com.example.math_for_kids.presentationlayer.view.screens.quiz.QuizScreen
import com.example.math_for_kids.middlelayer.viewmodel.QuizViewModel

sealed class QuizPage(val route: String) {
    data object QuizTest : QuizPage("test")
    data object QuizResult : QuizPage("result")
}

@Composable
fun QuizScreensStack() {
    val quizViewModel: QuizViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = QuizPage.QuizTest.route) {
        composable(QuizPage.QuizTest.route) { QuizScreen(navController, quizViewModel) }
        composable(QuizPage.QuizResult.route) { QuizResultScreen(navController, quizViewModel) }
    }
}