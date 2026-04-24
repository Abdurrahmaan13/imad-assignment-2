package com.example.yourappname

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoreText = findViewById<TextView>(R.id.txtScore)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val message = if (score > total / 2) {
            "Great job!"
        } else {
            "Keep practicing"
        }

        scoreText.text = "Score: $score/$total\n$message"
    }
}