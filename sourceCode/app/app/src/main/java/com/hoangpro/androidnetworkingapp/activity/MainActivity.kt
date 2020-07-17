package com.hoangpro.androidnetworkingapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.lab.lab1.Lab1Activity
import com.hoangpro.androidnetworkingapp.lab.lab2.Lab2Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        btnLab1.setOnClickListener {
            startActivity(Intent(this,Lab1Activity::class.java))
        }
        btnLab2.setOnClickListener {
            startActivity(Intent(this,Lab2Activity::class.java))
        }
    }
}