package com.example.math_for_kids.viewmodel

import androidx.lifecycle.ViewModel
import com.example.math_for_kids.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel: ViewModel() {
    private val level1Questions = listOf(
        Question("2 + 2", 4, listOf(4, 3, 5, 6)),
        Question("3 + 5", 8, listOf(8, 7, 6, 9)),
        Question("6 - 2", 4, listOf(2, 4, 3, 5)),
        Question("4 + 4", 8, listOf(7, 8, 5, 6)),
        Question("9 - 3", 6, listOf(6, 5, 7, 8))
    )

    private val level2Questions = listOf(
        Question("2 x 2", 4, listOf(4, 3, 5, 6)),
        Question("3 x 5", 15, listOf(15, 7, 6, 9)),
        Question("6 / 2", 3, listOf(2, 4, 3, 5)),
        Question("4 / 4", 1, listOf(7, 1, 5, 6)),
        Question("9 / 3", 3, listOf(3, 5, 4, 6))
    )

    val totalQuestions = 3
    private val maxLevel = 2

    private var currentQuestionIndex = 1

    private var selectedQuestions = listOf(Question("", 1, listOf(0, 0, 0, 0)))

    private val _currentQuestion = MutableStateFlow(selectedQuestions[currentQuestionIndex - 1])
    val currentQuestion: StateFlow<Question?> get() = _currentQuestion

    private val _feedbackText = MutableStateFlow("")
    val feedbackText: StateFlow<String> get() = _feedbackText

    private val _isNextEnabled = MutableStateFlow(false)
    val isNextEnabled: StateFlow<Boolean> get() = _isNextEnabled

    private val _questionNumber = MutableStateFlow(1)
    val questionNumber: StateFlow<Int> get() = _questionNumber

    private val _totalCorrectAnswers = MutableStateFlow(0)
    val totalCorrectAnswers: StateFlow<Int> get() = _totalCorrectAnswers

    private val _level = MutableStateFlow(1)
    val level: StateFlow<Int> get() = _level.asStateFlow()

    private fun fetchQuestions() {
        var questions: List<Question> = level1Questions

        when (_level.value) {
            1 -> questions = level1Questions
            2 -> questions = level2Questions
        }

        selectedQuestions = questions.shuffled().take(totalQuestions)
        _currentQuestion.value = selectedQuestions[currentQuestionIndex - 1]
    }

    fun checkAnswer(selectedAnswer: Int) {
        if (selectedAnswer == currentQuestion.value?.answer) {
            _feedbackText.value = "Correct!"
            _totalCorrectAnswers.value++
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
//            restartQuiz()
        }
    }

    fun restartQuiz() {
        currentQuestionIndex = 1
        selectedQuestions = listOf(Question("", 1, listOf(0, 0, 0, 0)))
        _currentQuestion.value = selectedQuestions[currentQuestionIndex - 1]
        _feedbackText.value = ""
        _isNextEnabled.value = false
        _questionNumber.value = 1
        _totalCorrectAnswers.value = 0
        fetchQuestions()
    }

    fun changeLevel() {
        if (_level.value != maxLevel) {
            _level.value += 1
        } else {
            _level.value = 1
        }
    }
}