package com.example.dualnback

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Oynanis : AppCompatActivity() {
    private lateinit var container : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oynanis)


        container = findViewById(R.id.containertext)
        val baslik1 = TextView(this)
        baslik1.text = "Dual N-Back Gameplay Walkthrough"
        baslik1.setTextColor(Color.BLACK)
        baslik1.textSize = 23f
        baslik1.setTypeface(null, Typeface.BOLD)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(12, 40, 0, 0)
        baslik1.layoutParams = layoutParams
        container.addView(baslik1)

        val  altbaslik= TextView(this)
        altbaslik.text = "1. What is the Game?"
        altbaslik.setTextColor(Color.BLACK)
        altbaslik.textSize = 20f
        altbaslik.setTypeface(null, Typeface.BOLD)
        altbaslik.layoutParams = layoutParams
        container.addView(altbaslik)

        val  altmetin= TextView(this)
        altmetin.text = "   Dual N-Back is a brain exercise game that tests both your visual and auditory memory abilities. The aim of the game is to remember the given signs for a given period of time and match them with the signs n steps before. Although the game has a simple structure, it can become difficult according to your choices."
        altmetin.setTextColor(Color.BLACK)
        altmetin.textSize = 13f
        altmetin.setTypeface(null, Typeface.ITALIC)
        altmetin.layoutParams = layoutParams
        container.addView(altmetin)

        val  altbaslik1= TextView(this)
        altbaslik1.text = "2. Login Screen and Settings"
        altbaslik1.setTextColor(Color.BLACK)
        altbaslik1.textSize = 20f
        altbaslik1.setTypeface(null, Typeface.BOLD)
        altbaslik1.layoutParams = layoutParams
        container.addView(altbaslik1)

        val altmetin1 = TextView(this)
        altmetin1.text = "   There are a few basic settings to customise your game layout:\n" +
                "\n" +
                "Matrix: Determines how many squares are in the game. This setting will determine the layout of the visual part of the game.\n" +
                "Game Step: Sets how many steps the game will take. You can set this setting to determine the total length of the game.\n" +
                "N-Back: This is the part where you choose how many steps before you need to remember. The higher this value, the higher the difficulty of the game."
        altmetin1.setTextColor(Color.BLACK)
        altmetin1.textSize = 13f
        altmetin1.setTypeface(null, Typeface.ITALIC)
        altmetin1.layoutParams = layoutParams
        container.addView(altmetin1)

        val  altbaslik2= TextView(this)
        altbaslik2.text = "3. Game Operation"
        altbaslik2.setTextColor(Color.BLACK)
        altbaslik2.textSize = 20f
        altbaslik2.setTypeface(null, Typeface.BOLD)
        altbaslik2.layoutParams = layoutParams
        container.addView(altbaslik2)

        val altmetin2 = TextView(this)
        altmetin2.text = "  The game has a fairly simple mechanic, but can become difficult depending on your choices:\n" +
                "\n" +
                "Start: When the game starts, a marker will appear on the matrix.\n" +
                "Matching: In each round, a marker is shown on the matrix and the user tries to remember the marker given n steps before.\n" +
                "For example, if n is 3, when you get to step 4 of the game, you will need to remember the sign given in step 1.\n" +
                "Correct Matching: You can make the correct match by clicking. In this way, you will score points."
        altmetin2.setTextColor(Color.BLACK)
        altmetin2.textSize = 13f
        altmetin2.setTypeface(null, Typeface.ITALIC)
        altmetin2.layoutParams = layoutParams
        container.addView(altmetin2)

        val  altbaslik3= TextView(this)
        altbaslik3.text = "4. Example Scenario"
        altbaslik3.setTextColor(Color.BLACK)
        altbaslik3.textSize = 20f
        altbaslik3.setTypeface(null, Typeface.BOLD)
        altbaslik3.layoutParams = layoutParams
        container.addView(altbaslik3)

        val altmetin3 = TextView(this)
        altmetin3.text = "N value 3:\n" +
                "The game has started.\n" +
                "a sign is shown on the step.\n" +
                "another sign was shown on the step.\n" +
                "a different sign was shown in step 1.\n" +
                "in step 1 you are asked to remember the sign given in step 1."
        altmetin3.setTextColor(Color.BLACK)
        altmetin3.textSize = 13f
        altmetin3.setTypeface(null, Typeface.ITALIC)
        altmetin3.layoutParams = layoutParams
        container.addView(altmetin3)

        val  altbaslik4= TextView(this)
        altbaslik4.text = "5. Result"
        altbaslik4.setTextColor(Color.BLACK)
        altbaslik4.textSize = 20f
        altbaslik4.setTypeface(null, Typeface.BOLD)
        altbaslik4.layoutParams = layoutParams
        container.addView(altbaslik4)

        val altmetin4 = TextView(this)
        altmetin4.text = "  Dual N-Back is a fun and challenging game that allows you to test your attention and memory. By increasing the difficulty level as the game progresses, you can challenge your brain in various ways. Each correct match brings you closer to a higher score and helps you improve your memory capacity."
        altmetin4.setTextColor(Color.BLACK)
        altmetin4.textSize = 13f
        altmetin4.setTypeface(null, Typeface.ITALIC)
        altmetin4.layoutParams = layoutParams
        container.addView(altmetin4)

        val oyunagec = TextView(this)
        oyunagec.text = "Go to game ->\n"
        oyunagec.setTextColor(Color.BLACK)
        oyunagec.textSize = 17f
        oyunagec.setTypeface(null, Typeface.BOLD)
        oyunagec.layoutParams = layoutParams
        container.addView(oyunagec)

        oyunagec.setOnClickListener{
            startActivity(Intent(this,DualNBackActivity::class.java))
            finish()
        }

        container.setBackgroundColor(Color.parseColor("#ffc0cb"))

    }
}
