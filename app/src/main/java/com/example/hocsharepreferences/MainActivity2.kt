package com.example.hocsharepreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val weightText=findViewById<EditText>(R.id.edWeight)
        val heightText=findViewById<EditText>(R.id.edHeight)
        val btn = findViewById<Button>(R.id.btnTinh)
        btn.setOnClickListener {
            val height = heightText.text.toString().toDouble()
            val weight=weightText.text.toString().toDouble()/100 // chuyen don vi cm ->m
            val bmi = weight/ (weight*weight)
            val bmiDigits = String.format("%.2f",bmi).toDouble()
            displayResult(bmiDigits)

        }

    }
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when {
            weight.isNullOrEmpty() || height.isNullOrEmpty() ->{
                Toast.makeText(this, "Not Empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }

    private fun displayResult(bmiDigits: Double) {
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmiDigits.toString()
        info.text = "(Normal range is 18.5 - 24.9 )"
        var resultText = ""
        var color = 0
        when {
            bmiDigits< 18.50 ->{
                resultText="Underweight"
                color=R.color.under_weight
            }
            bmiDigits in 18.50..24.49 ->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmiDigits in 25.00 .. 29.99 ->{
                resultText="Overweight"
                color=R.color.over_weight
            }
            bmiDigits > 29.99 -> {
                resultText="Obese"
                color=R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText

    }
}