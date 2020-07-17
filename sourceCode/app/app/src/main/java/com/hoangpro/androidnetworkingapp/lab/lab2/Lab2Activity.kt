package com.hoangpro.androidnetworkingapp.lab.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab2.*

class Lab2Activity : AppCompatActivity() {
    companion object{
       val  url="http://192.168.43.65:8120"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2)
        setupView()
    }

    private fun setupView() {
        btnTask1.setOnClickListener {
            startActivity(Intent(this,Lab2Task1Activity::class.java))
        }
        btnTask2.setOnClickListener {
            startActivity(Intent(this,Lab2Task2Activity::class.java))
        }
        btnTask3.setOnClickListener {
            startActivity(Intent(this,Lab2Task3Activity::class.java))
        }
        btnTask4.setOnClickListener {
            startActivity(Intent(this,Lab2Task4Activity::class.java))
        }
    }
}