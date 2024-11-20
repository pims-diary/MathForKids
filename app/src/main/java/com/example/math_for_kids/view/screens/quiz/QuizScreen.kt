package com.example.math_for_kids.view.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.QuizPage
import com.example.math_for_kids.storage.getLevel
import com.example.math_for_kids.view.components.OptionsGrid
import com.example.math_for_kids.viewmodel.QuizViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(navController: NavHostController, viewModel: QuizViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val questionState by viewModel.currentQuestion.collectAsState()
    val feedbackText by viewModel.feedbackText.collectAsState()
    val isNextEnabled by viewModel.isNextEnabled.collectAsState()
    val questionNumber by viewModel.questionNumber.collectAsState()

    val previousScreen = remember {
        navController.previousBackStackEntry?.destination?.route
    }

    LaunchedEffect(previousScreen) {
        // Check if User is navigating from Result Screen or not
        if (previousScreen != "result") {
            // If User is not navigating from Result Screen, that
            // means User is navigating from Home screen. So we
            // need to get the player level from dataStore
            scope.launch {
                val newLevel = getLevel(context).first()
                viewModel.setLevel(newLevel = newLevel)
                viewModel.restartQuiz()
            }
        } else {
            // If User is navigating from Results Screen, that
            // means User does not need to fetch player level
            // from dataStore as that has already been done
            // on Results Screen. Simply restart the quiz.
            viewModel.restartQuiz()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Question $questionNumber",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = questionState?.question ?: "",
            fontSize = 80.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )

        // Options Grid (2x2)
        OptionsGrid(
            options = questionState!!.options,
            onOptionSelected = { selectedAnswer ->
                viewModel.checkAnswer(selectedAnswer)
            },
            enabled = !isNextEnabled
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = feedbackText,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        if (questionNumber != viewModel.totalQuestions) {
            Button(
                onClick = { viewModel.loadNextQuestion() },
                enabled = isNextEnabled,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Next")
            }
        } else {
            Button(
                onClick = { navController.navigate(QuizPage.QuizResult.route) },
                enabled = isNextEnabled,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("See Result")
            }
        }
    }
}
