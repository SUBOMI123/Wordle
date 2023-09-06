package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var guessCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordSource = FourLetterWordList
        val guessButton = findViewById<Button>(R.id.guessButton)

        // textViews
        val Guess1 = findViewById<TextView>(R.id.guess1)
        val Guess1Check = findViewById<TextView>(R.id.guess1Eval)
        val Guess2 = findViewById<TextView>(R.id.guess2)
        val Guess2Check = findViewById<TextView>(R.id.guess2Eval)
        val Guess3 = findViewById<TextView>(R.id.guess3)
        val Guess3Check = findViewById<TextView>(R.id.guess3Eval)
        val correctWordView = findViewById<TextView>(R.id.correctWord)
        val userGuess = findViewById<EditText>(R.id.userGuess)

        var correctWord = wordSource.getRandomFourLetterWord()

        guessButton.setOnClickListener {
            val userGuessText = userGuess.text
            userGuess.setText("")
            val userGuessEval = checkGuess(userGuessText.toString().uppercase(), correctWord)

            guessCount++

            if (guessCount == 1) {
                Guess1.text = userGuessText.toString().uppercase()
                Guess1Check.text = userGuessEval
            } else if (guessCount == 2) {
                Guess2.text = userGuessText.toString().uppercase()
                Guess2Check.text = userGuessEval
            } else if (guessCount == 3) {
                Guess3.text = userGuessText.toString().uppercase()
                Guess3Check.text = userGuessEval
            }

            if (userGuessText.toString().uppercase() == correctWord) {  // user guessed correctly
                // show toast that user won
                val toast = Toast.makeText(this, getString(R.string.win), Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 10)
                toast.show()
                // disable submit button and text input field
                guessButton.isEnabled = false
                userGuess.isEnabled = false
            } else if (guessCount == 3){  // user did not guess correctly on last try
                // show correct answer
                correctWordView.text = correctWord
                correctWordView.visibility = View.VISIBLE
                // disable submit button and text input field
                guessButton.isEnabled = false
                userGuess.isEnabled = false
            }
        }
    }
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}