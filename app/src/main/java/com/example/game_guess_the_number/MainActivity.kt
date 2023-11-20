package com.example.game_guess_the_number;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.game_guess_the_number.R.*

class MainActivity : AppCompatActivity() {

    lateinit var beginIntent: EditText
    lateinit var endIntent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        beginIntent = findViewById(R.id.begin)
        endIntent = findViewById(R.id.end)
    }

    fun onGuessClick(view: View) {

        val intent = Intent(this, GameActivity::class.java)

        var begin = beginIntent.text.toString()
        var end = endIntent.text.toString()

        if (begin.isEmpty()) {
            begin = "0"
        }
        if (end.isEmpty()) {
            end = "100"
        }
        if (begin == end) {
            Toast.makeText(this, "Error: start and end are equal!!!", Toast.LENGTH_SHORT).show()
        } else {
            intent.putExtra("end", end.toInt())
            intent.putExtra("begin", begin.toInt())
            startActivity(intent)
        }

    }
}