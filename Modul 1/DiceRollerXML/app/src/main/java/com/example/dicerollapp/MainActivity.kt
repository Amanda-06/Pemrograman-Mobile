package com.example.dicerollapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dice1: ImageView = findViewById(R.id.dice1)
        val dice2: ImageView = findViewById(R.id.dice2)
        val btnRoll: Button = findViewById(R.id.btnRoll)

        btnRoll.setOnClickListener { view ->
            btnRoll.isEnabled = false

            dice1.animate().rotationBy(360f).setDuration(120)
            dice2.animate().rotationBy(360f).setDuration(120).withEndAction {

                val randomDice1 = (1..6).random()
                val randomDice2 = (1..6).random()

                dice1.setImageResource(getRandomDiceImage(randomDice1))
                dice2.setImageResource(getRandomDiceImage(randomDice2))

                val message = if (randomDice1 == randomDice2) {
                    "Selamat, anda dapat dadu double!"
                } else {
                    "Anda belum beruntung!"
                }

                val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                snackbar.setTextColor(android.graphics.Color.WHITE) // Warnai teksnya putih
                snackbar.show()

                btnRoll.isEnabled = true
            }
        }
    }

    private fun getRandomDiceImage(randomInt: Int): Int {
        return when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}