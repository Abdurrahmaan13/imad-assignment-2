package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val text: String,
    val isTrue: Boolean,
    val explanation: String
)

class QuizActivity : AppCompatActivity() {

    private var currentIndex = 0
    private var score = 0
    private var hasAnswered = false

    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    private val questions = listOf(
        Question(
            text = "Drinking coffee can improve alertness",
            isTrue = true,
            explanation = "Caffeine can help boost alertness and focus."
        ),
        Question(
            text = "You should charge your phone overnight every day",
            isTrue = false,
            explanation = "Charging overnight regularly may affect battery health over time."
        ),
        Question(
            text = "Closing apps always saves phone battery",
            isTrue = false,
            explanation = "Modern phones manage background apps automatically."
        ),
        Question(
            text = "Writing tasks down can improve memory",
            isTrue = true,
            explanation = "Writing things down can help with memory and retention."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.txtQuestion)
        feedbackText = findViewById(R.id.txtFeedback)
        trueButton = findViewById(R.id.btnTrue)
        falseButton = findViewById(R.id.btnFalse)
        nextButton = findViewById(R.id.btnNext)

        showQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            goToNextQuestion()
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentIndex].text
        feedbackText.text = ""
        hasAnswered = false

        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (hasAnswered) return

        val currentQuestion = questions[currentIndex]

        if (userAnswer == currentQuestion.isTrue) {
            score++
            feedbackText.text = "Correct! ${currentQuestion.explanation}"
        } else {
            feedbackText.text = "Wrong! ${currentQuestion.explanation}"
        }

        hasAnswered = true
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun goToNextQuestion() {
        if (!hasAnswered) {
            feedbackText.text = "Please answer before going to the next question."
            return
        }

        currentIndex++

        if (currentIndex < questions.size) {
            showQuestion()
        } else {
            openScoreActivity()
        }
    }

    private fun openScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questions.size)

        startActivity(intent)
        finish()
    }
}