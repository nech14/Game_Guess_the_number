package com.example.game_guess_the_number;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.game_guess_the_number.R

class GameActivity : AppCompatActivity() {
    var begin: Int = 0
    var end: Int = 100
    var thisNumber = 0
    var victory = false
    lateinit var tvQuestion: TextView
    lateinit var restartActivity: Intent
    var startNewActivity = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        startNewActivity = false
        restartActivity = Intent(this, MainActivity::class.java)
        begin = intent.getIntExtra("begin", begin)
        end = intent.getIntExtra("end", end)
        Log.e("ggg", "$begin, $end")
        thisNumber = ((end - begin) / 2 + 0.9).toInt() + begin
        tvQuestion = findViewById<TextView>(R.id.question)
        tvQuestion.text =
            getString(R.string.is_your_hidden_number_greater_than_or_equal_to, thisNumber)
    }


    fun mainAction(currentNumber: Int, minuendBegin: Boolean) {
        if (minuendBegin) {
            begin = currentNumber + 1
        } else {
            end = currentNumber
        }
        thisNumber = ((end - begin) / 2 + 0.9).toInt() + begin
    }


    fun onYesNoClick(view: View) {

        val answer = view.id == R.id.yes

        Log.d("mytag", "$answer, $begin, $end, $thisNumber")
        if (!victory) mainAction(thisNumber, answer)
        else if (!startNewActivity) if (answer) {
            startNewActivity = true
            startActivity(restartActivity)
        } else Toast.makeText(
            this, getString(R.string.why), Toast.LENGTH_SHORT
        ).show()

        when (end - begin + 1) {
            1 -> {
                tvQuestion.text = getString(R.string.you_guessed_the_number_restart, thisNumber)
                victory = true
            }

            2 -> {
                tvQuestion.text = getString(R.string.did_you_wish_for_the_number, 1 + thisNumber)
            }

            else -> tvQuestion.text =
                getString(R.string.is_your_hidden_number_greater_than_or_equal_to, thisNumber)
        }

    }


}