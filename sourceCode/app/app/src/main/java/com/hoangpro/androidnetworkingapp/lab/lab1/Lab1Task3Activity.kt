package com.hoangpro.androidnetworkingapp.lab.lab1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab1_task2.*
import java.net.URL

class Lab1Task3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task3)
        val handleImage = HandleImage {
            imgResult.setImageBitmap(it)
        }

        handleImage.execute("https://i.ytimg.com/vi/tVlcKp3bWH8/maxresdefault.jpg")
    }

    inner class HandleImage(onImageResult:(bitmap:Bitmap)->Unit):AsyncTask<String,Void,Bitmap>(){
        private val onImageResult=onImageResult
        override fun doInBackground(vararg params: String?): Bitmap {
            return getBitmapFromInternet(params[0]?:"")
        }

        override fun onPostExecute(result: Bitmap?) {
            onImageResult(result!!)
            super.onPostExecute(result)
        }

        private fun getBitmapFromInternet(url: String):Bitmap{
            val url = URL(url)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        }
    }
}