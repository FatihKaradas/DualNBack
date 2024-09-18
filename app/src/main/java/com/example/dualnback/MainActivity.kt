package com.example.dualnback

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dualnback.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {

    private lateinit var tw : TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var myspinner: Spinner
    private lateinit var spinnerAdapter : ArrayAdapter<String>
    private var sayi:Int = 2
    private lateinit var spinnerGameStep : Spinner
    private lateinit var gameStepAdapter : ArrayAdapter<Int>
    private var gameStep : Int = 10
    private lateinit var spinnerNback : Spinner
    private lateinit var nBackAdapter : ArrayAdapter<Int>
    private var n : Int = 2
    private lateinit var startbuton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startbuton = findViewById(R.id.StartButton)
        tw = findViewById(R.id.nasıloynanır)
        tw.setOnClickListener{
            startActivity(Intent(this,Oynanis::class.java))
        }

        myspinner = findViewById(R.id.mySpinner)
        val matris = arrayOf("(2x2)","(3x3)","(4x4)","(5x5)","(6x6)")
        spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,matris)
        myspinner.adapter = spinnerAdapter

        myspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, index: Int, id: Long) {
                sayi = when(matris[index]){
                    "(2x2)" -> 2
                    "(3x3)" -> 3
                    "(4x4)" -> 4
                    "(5x5)" -> 5
                    "(6x6)" -> 6
                    else ->2
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerGameStep = findViewById(R.id.spinnergameStep)
        val gamestepnumber = arrayOf(10,11,12,13,14,15,16,17,18,19,20)
        gameStepAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,gamestepnumber)
        spinnerGameStep.adapter = gameStepAdapter

        spinnerGameStep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                gameStep = gamestepnumber[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerNback = findViewById(R.id.spinnerNago)
        val nBackNumber = arrayOf(2,3,4,5)
        nBackAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,nBackNumber)
        spinnerNback.adapter = nBackAdapter

        spinnerNback.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                n = nBackNumber[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        startbuton.setOnClickListener{
            val intent = Intent(this,DualNBackActivity::class.java)
            intent.putExtra("matris",sayi)
            intent.putExtra("gamestep",gameStep)
            intent.putExtra("nback",n)
            startActivity(intent)
        }
    }
   /* private fun createMatris(size: Int) {
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
    }
     private val randomNumberList = mutableListOf<Int>()
     private var nStepsAgoNumber: Int? = null
     private val nStepAgoNumberList = mutableListOf<Int>()
    @SuppressLint("ResourceType")
    private fun randomSelect() {
        val random = Random() // Random nesnesi oluştur, seed kullanmadan
        if (stepcount<10) {

            val randomNumber = random.nextInt(sayi * sayi)
            randomNumberList.add(randomNumber)

            val selectedimageview = imageViewList[randomNumber]
            selectedimageview.setImageResource(R.drawable.square2)

            if (stepcount>=n){
                nStepsAgoNumber = randomNumberList[stepcount-n]
                nStepAgoNumberList.add(nStepsAgoNumber!!)
            }

            Handler(Looper.getMainLooper()).postDelayed({
                selectedimageview.setImageResource(R.drawable.square)

                Handler(Looper.getMainLooper()).postDelayed({
                    stepcount++
                    randomSelect()
                }, 250)

            }, 1500)
        }
        else{
            stepcount = 0
            Log.e("Random Sayı:",randomNumberList.toString())
            Log.e("N step ago number:",nStepAgoNumberList.toString())
            randomNumberList.clear()
            nStepAgoNumberList.clear()
        }
    }*/



}
