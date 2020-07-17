package com.hoangpro.androidnetworkingapp.lab.lab1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab1_task1.*
import java.net.URL

class Lab1Task1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task1)
        setupView()
    }

    private fun setupView() {
        btnLoadImg.setOnClickListener {
            val thread = Thread(Runnable {
                val bitmap = getBitmapFromInternet("https://giadinh.mediacdn.vn/thumb_w/640/2019/10/30/ngoc-trinh-5-1572423107231301246873-crop-15724237122011649225014.jpg")
                imgResult.post(Runnable {
                    imgResult.setImageBitmap(bitmap)
                })
            })
            thread.start()
        }
    }
    private fun getBitmapFromInternet(url: String):Bitmap{
        val url = URL(url)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}