package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.math_for_kids.viewmodel.QuizViewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    val questionState by viewModel.currentQuestion.collectAsState()
    val feedbackText by viewModel.feedbackText.collectAsState()
    val isNextEnabled by viewModel.isNextEnabled.collectAsState()
    val questionNumber by viewModel.questionNumber.collectAsState()

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
            }
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
                onClick = { viewModel.loadNextQuestion() },
                enabled = isNextEnabled,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("See Result")
            }
        }
    }
}

@Composable
fun OptionsGrid(options: List<Int>, onOptionSelected: (Int) -> Unit) {
    Column {
        for (i in options.indices step 2) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                OptionButton(option = options[i], onOptionSelected = onOptionSelected)
                OptionButton(option = options[i + 1], onOptionSelected = onOptionSelected)
            }
        }
    }
}

@Composable
fun OptionButton(option: Int, onOptionSelected: (Int) -> Unit) {
    Button(onClick = { onOptionSelected(option) }) {
        Text(text = option.toString())
    }
}