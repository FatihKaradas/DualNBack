package com.example.dualnback

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dualnback.databinding.ActivityMainBinding
import java.util.Random


abstract class MainActivity : AppCompatActivity() {

    private var stepcount = 0
    private val imageViewList = mutableListOf<ImageView>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var spinner: Spinner
    private lateinit var spinnerAdapter : ArrayAdapter<String>
    private var sayi:Int = 0
    private lateinit var container : RelativeLayout
    private lateinit var startbuton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startbuton = findViewById(R.id.StartButton)
        container = findViewById(R.id.container)
        spinner = findViewById(R.id.mySpinner)
        val matris = arrayOf("(2x2)","(3x3)","(4x4)","(5x5)","(6x6)")
        spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,matris)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, index: Int, id: Long) {
                sayi = when(matris[index]){
                    "(2x2)" -> 2
                    "(3x3)" -> 3
                    "(4x4)" -> 4
                    "(5x5)" -> 5
                    "(6x6)" -> 6
                    else ->2
                }
                createMatris(sayi)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        startbuton.setOnClickListener{
            randomSelect()
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

                // ImageView'i container'a ekle
                container.addView(imageView)

                // ImageView'i global listeye ekle
                imageViewList.add(imageView)
            }
        }
        println(imageViewList)
    }
    private var correctcount = 0
    private var n:Int = 2
    private val randomNumbersList = mutableListOf<Int>()
    private var nStepsAgoNumber: Int? = null
    private val nStepAgoNumberList = mutableListOf<Int>()
    @SuppressLint("ResourceType")
    private fun randomSelect() {
        val random = Random() // Random nesnesi oluştur, seed kullanmadan
        if (stepcount<10) {
            val randomNumber = random.nextInt(sayi * sayi)
            randomNumbersList.add(randomNumber)// 0 ile sayi*sayi arasında rastgele bir sayı seç
            val selectedimageview = imageViewList[randomNumber]
            selectedimageview.setImageResource(R.drawable.square2)
            if (stepcount>=n){
                nStepsAgoNumber = randomNumbersList[stepcount - n]
                nStepAgoNumberList.add(nStepsAgoNumber!!)
            }
            val selectednstepagoImageView=imageViewList[nStepsAgoNumber!!]
            selectednstepagoImageView.setOnClickListener{
                correctcount+1
                selectednstepagoImageView.isClickable = false
            }
            Handler(Looper.getMainLooper()).postDelayed({
                selectedimageview.setImageResource(R.drawable.square)
                Handler(Looper.getMainLooper()).postDelayed({
                    selectednstepagoImageView.isClickable = true
                    stepcount++
                    randomSelect()
                }, 250)
            }, 1250)
        }
        else{
            Log.d("RandomNumbers", randomNumbersList.joinToString(", "))
            Log.d("TwoStepAgoNumbers", nStepAgoNumberList.joinToString(", "))
            Toast.makeText(applicationContext,"Doğru Eşleştirme: ${correctcount}",Toast.LENGTH_SHORT).show()
            stepcount = 0
            correctcount = 0
            randomNumbersList.clear()
            nStepAgoNumberList.clear()
        }
    }

}
