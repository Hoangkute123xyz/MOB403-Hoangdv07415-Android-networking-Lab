package com.hoangpro.androidnetworkingapp.lab.lab1

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab1_task2.*
import java.net.URL

class Lab1Task2Activity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task2)
        handler = Handler(){
            imgResult.setImageBitmap(bitmap)
            handler.postDelayed(Runnable {
                startActivity(Intent(this,Lab1Task1Activity::class.java))
                finish()
            },3000)
            return@Handler true
        }
        val thread = Thread(Runnable {
            bitmap = getBitmapFromInternet("https://sites.google.com/site/hinhanhdep24h/_/rsrc/1436687439788/home/hinh%20anh%20thien%20nhien%20dep%202015%20%281%29.jpeg")?:return@Runnable
            handler.sendMessage(Message())
        })
        thread.start()
    }

    private fun getBitmapFromInternet(url: String): Bitmap? {
        try {
            val url = URL(url)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}