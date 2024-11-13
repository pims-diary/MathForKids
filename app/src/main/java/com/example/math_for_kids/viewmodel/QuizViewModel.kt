package com.example.math_for_kids.viewmodel

import androidx.lifecycle.ViewModel
import com.example.math_for_kids.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel: ViewModel() {
    private val questions = listOf(
        Question("2 + 2", 4, listOf(4, 3, 5, 6)),
        Question("3 + 5", 8, listOf(8, 7, 6, 9)),
        Question("6 - 2", 4, listOf(2, 4, 3, 5)),
        Question("4 + 4", 8, listOf(7, 8, 5, 6)),
        Question("9 - 3", 6, listOf(6, 5, 7, 8))
    )

    val totalQuestions = 3

    private var selectedQuestions = questions.shuffled().take(totalQuestions)

    private var currentQuestionIndex = 1
    private val _currentQuestion = MutableStateFlow(selectedQuestions[currentQuestionIndex - 1])
    val currentQuestion: StateFlow<Question?> get() = _currentQuestion

    private val _feedbackText = MutableStateFlow("")
    val feedbackText: StateFlow<String> get() = _feedbackText

    private val _isNextEnabled = MutableStateFlow(false)
    val isNextEnabled: StateFlow<Boolean> get() = _isNextEnabled

    private val _questionNumber = MutableStateFlow(1)
    val questionNumber: StateFlow<Int> get() = _questionNumber

    fun checkAnswer(selectedAnswer: Int) {
        if (selectedAnswer == currentQuestion.value?.answer) {
            _feedbackText.value = "Correct!"
        } else {
            _feedbackText.value = "Wrong!"
        }
        _isNextEnabled.value = true
    }

    fun loadNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex <= selectedQuestions.size) {
            _currentQuestion.value = selectedQuestions[currentQuestionIndex - 1]
            _feedbackText.value = ""
            _isNextEnabled.value = false
            _questionNumber.value = currentQuestionIndex
        } else {
            // Reset for demonstration, could show a results screen instead
            resetQuiz()
        }
    }

    private fun resetQuiz() {
        selectedQuestions = questions.shuffled().take(totalQuestions)
        currentQuestionIndex = 1
        _currentQuestion.value = selectedQuestions[currentQuestionIndex - 1]
        _feedbackText.value = ""
        _isNextEnabled.value = false
        _questionNumber.value = 1
    }
}