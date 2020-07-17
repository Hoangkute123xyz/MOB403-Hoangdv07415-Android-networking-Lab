package com.hoangpro.androidnetworkingapp.lab.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab1.*

class Lab1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1)
        setupView()
    }

    private fun setupView() {
        btnTask1.setOnClickListener {
            startActivity(Intent(this,Lab1Task1Activity::class.java))
        }
        btnTask2.setOnClickListener {
            startActivity(Intent(this,Lab1Task2Activity::class.java))
        }
        btnTask3.setOnClickListener {
            startActivity(Intent(this,Lab1Task3Activity::class.java))
        }
        btnTask4.setOnClickListener {
            startActivity(Intent(this,Lab1Task4Activity::class.java))
        }
    }
}