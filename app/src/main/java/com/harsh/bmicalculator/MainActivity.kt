package com.harsh.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var weight : EditText
    private lateinit var height : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weight = findViewById(R.id.eTWeight)
        height = findViewById(R.id.eTHeight)
        val button = findViewById<Button>(R.id.btnCal)

        button.setOnClickListener {
                val w = weight.text.toString()
                val h = height.text.toString()
                if(validate(w,h)) {
                    val bmi = w.toFloat() / ((h.toFloat() / 100) * (h.toFloat() / 100))
                    val bmi2 = String.format("%.2f", bmi).toFloat()
                    displayResult(bmi2)
                }
        }

    }

    private fun validate(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight Shouldn't Be Empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height Shouldn't Be Empty",Toast.LENGTH_SHORT).show()
                return false
            }else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val result = findViewById<TextView>(R.id.idIndex)
        val des = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        result.text = bmi.toString()
        info.text = "(The Normal Range is 18.5 - 24.9)"

        var res = ""
        var information = ""
        var color = 0
        when{
            bmi < 18.50 ->{
                res = "UnderWeight"
                color = R.color.under
            }
            bmi in 18.50..24.99->{
                res = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                res = "Over Weight"
                color = R.color.red
            }
            bmi > 29.99 ->{
                res = "Obese"
                color = R.color.obese
            }
        }
        des.setTextColor(ContextCompat.getColor(this,color))
        des.text = res

    }

}