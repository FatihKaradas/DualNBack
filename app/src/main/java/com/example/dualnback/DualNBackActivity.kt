package com.example.dualnback

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class DualNBackActivity : AppCompatActivity() {
    private lateinit var score : TextView
    private var stepcount : Int = 0
    private var gameStep : Int = 10
    private var n : Int = 2
    private var sayi : Int = 2
    private val imageViewList = mutableListOf<ImageView>()
    private lateinit var container : RelativeLayout
    private lateinit var baslabuton : Button
    private lateinit var sayıcı : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dual_nback)

        score = findViewById(R.id.score)
        container = findViewById(R.id.container)
        baslabuton = findViewById(R.id.başlaButon)
        sayıcı = findViewById(R.id.sayıcı)

        gameStep = intent.getIntExtra("gamestep",10)
        n = intent.getIntExtra("nback",2)
        sayi = intent.getIntExtra("matris",2)
        Log.e("game step:",gameStep.toString())
        Log.e("n back:",n.toString())
        Log.e("matris:",sayi.toString())
        createMatris(sayi)

        baslabuton.setOnClickListener{
            val sayici = object : CountDownTimer(4000,1000) {
                override fun onTick(millisUntilFinished: Long) {
                    sayıcı.text = "${millisUntilFinished/1000}"
                    sayıcı.setTextColor(Color.BLACK)
                    sayıcı.textSize = 30f
                    sayıcı.setTypeface(null, Typeface.BOLD)
                }
                override fun onFinish() {
                    sayıcı.text = ""
                    score.text = "Score : 0"
                    randomSelect()
                }
            }
            sayici.start()
        }
    }

    private fun createMatris(size: Int) {
        container.removeAllViews() // Mevcut tüm görüntüleri temizle
        imageViewList.clear() // ImageView listemizi temizle

        val dp = 300
        val scale = resources.displayMetrics.density
        val pixels = (dp * scale).toInt()

        val cellSize = pixels / size // Her bir hücrenin boyutunu belirle
        val marginSize = 4 // ImageView aralarındaki boşluk için margin

        for (i in 0 until size) {
            for (j in 0 until size) {
                val imageView = ImageView(container.context)

                // ImageView'in boyutunu ve konumunu ayarla
                val imageParams = RelativeLayout.LayoutParams(cellSize - marginSize, cellSize - marginSize)

                // Her ImageView'e belirli bir yerleştirme kuralları ekle
                imageParams.leftMargin = j * cellSize
                imageParams.topMargin = i * cellSize

                imageView.layoutParams = imageParams
                imageView.setImageResource(R.drawable.square) // Örnek olarak bir image resource kullan

                imageView.tag = "$i,$j"

                // ImageView'i container'a ekle
                container.addView(imageView)

                // ImageView'i global listeye ekle
                imageViewList.add(imageView)
            }
        }
    }
    private var count : Int = 0
    private val randomNumberList = mutableListOf<Int>()
    private var nStepsAgoNumber: Int? = null
    private val nStepAgoNumberList = mutableListOf<Int>()
    private lateinit var nStepAgoNumberImageView : ImageView
    @SuppressLint("ResourceType")
    private fun randomSelect() {
        val random = Random() // Random nesnesi oluştur, seed kullanmadan
        if (stepcount<gameStep) {

            val randomNumber = random.nextInt(sayi * sayi)
            randomNumberList.add(randomNumber)

            val selectedimageview = imageViewList[randomNumber]
            selectedimageview.setImageResource(R.drawable.square2)

            if (stepcount>=n){
                nStepsAgoNumber = randomNumberList[stepcount-n]
                nStepAgoNumberList.add(nStepsAgoNumber!!)
                nStepAgoNumberImageView = imageViewList[nStepsAgoNumber!!]
            }

            imageViewList.forEach { imageView ->
                imageView.setOnClickListener {
                    if (stepcount >= n){
                        if (imageView == nStepAgoNumberImageView) {
                            count++
                            imageView.isClickable = false
                            Toast.makeText(applicationContext,"Correct Match",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext,"Incorrect Match",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext,"Incorrect Match",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({
                selectedimageview.setImageResource(R.drawable.square)

                Handler(Looper.getMainLooper()).postDelayed({
                    imageViewList.forEach { imageView ->
                        imageView.isClickable = true
                    }
                    score.text = "Score : ${count} "
                    stepcount++
                    randomSelect()
                }, 250)

            }, 1500)
        }
        else{
            nStepsAgoNumber = null
            count = 0
            stepcount = 0
            Log.e("Random Sayı:",randomNumberList.toString())
            Log.e("N step ago number:",nStepAgoNumberList.toString())
            randomNumberList.clear()
            nStepAgoNumberList.clear()
        }
    }
}

