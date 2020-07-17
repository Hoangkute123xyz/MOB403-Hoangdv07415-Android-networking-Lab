package com.hoangpro.androidnetworkingapp.lab.lab2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_lab2_task2.btnSubmit
import kotlinx.android.synthetic.main.activity_lab2_task3.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.lang.reflect.Executable
import java.net.HttpURLConnection
import java.net.URL

class Lab2Task3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2_task3)
        btnSubmit.setOnClickListener {
            val loading = DialogHelper.showDialogLoading(this)
            val handleCubeVolume = HandleCubeVolume("${Lab2Activity.url}/cube-volume-calculate"){
                tvResult.text=it
                loading.dismiss()
            }
            handleCubeVolume.execute(edtLength.text.toString().toFloat())
        }
    }

    inner class HandleCubeVolume(link:String,onResult:(result:String)->Unit):AsyncTask<Float,Void,String>(){
        private val link = link
        private val onResult = onResult
        override fun doInBackground(vararg params: Float?): String {
            var result = ""
            try {
                val url = URL(link)
                val connection = url.openConnection() as HttpURLConnection
                val body="value=${params[0]}"
                connection.apply {
                    doOutput=true
                    requestMethod="POST"
                    setRequestProperty("Content-Type","application/x-www-form-urlencoded")
                    setFixedLengthStreamingMode(body.toByteArray().size)
                }
                val printWriter = PrintWriter(connection.outputStream)
                printWriter.print(body)
                printWriter.close()

                val bufferReader = BufferedReader(InputStreamReader(connection.inputStream))
                var value = bufferReader.readLine()
                while (value!=null){
                    result+=value
                    value=bufferReader.readLine()
                }
                connection.disconnect()
            } catch (e:Exception){
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            onResult(result!!)
            super.onPostExecute(result)
        }

    }
}