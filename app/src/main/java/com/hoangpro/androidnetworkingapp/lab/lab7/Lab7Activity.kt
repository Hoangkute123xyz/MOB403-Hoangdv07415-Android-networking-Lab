package com.hoangpro.androidnetworkingapp.lab.lab7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab7.*

class Lab7Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab7)

        btnClient.setOnClickListener {
            startActivity(Intent(this,ClientActivity::class.java))
        }
        btnServer.setOnClickListener {
            startActivity(Intent(this,ServerActivity::class.java))
        }
    }
}