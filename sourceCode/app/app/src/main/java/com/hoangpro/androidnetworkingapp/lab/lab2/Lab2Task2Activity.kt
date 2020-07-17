package com.hoangpro.androidnetworkingapp.lab.lab2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_lab2_task2.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class Lab2Task2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2_task2)
        btnSubmit.setOnClickListener {
            val loading = DialogHelper.showDialogLoading(this)
            val handleSquare = HandleSquare("${Lab2Activity.url}/square-calculate"){
                tvReuslt.text=it
                loading.dismiss()
            }
            handleSquare.execute(edtWidth.text.toString().toFloat(),edtLength.text.toString().toFloat())
        }
    }

    inner class HandleSquare(link:String,onResult:(result:String)->Unit):AsyncTask<Float,Void,String>(){
        private val onResult=onResult
        private val link = link
        override fun doInBackground(vararg params: Float?): String {
            var result=""
            try{
                val url = URL("$link")
                val connection = url.openConnection() as HttpURLConnection
                val body="width=${params[0]}&length=${params[1]}"

                connection.apply {
                    doOutput=true
                    requestMethod="POST"
                    setFixedLengthStreamingMode(body.toByteArray().size)
                    setRequestProperty("Content-Type","application/x-www-form-urlencoded")
                }

                val printWriter = PrintWriter(connection.outputStream)
                printWriter.print(body)
                printWriter.close()

                val bufferReader = BufferedReader(InputStreamReader(connection.inputStream))
                var value = bufferReader.readLine()
                while (value!=null){
                    result+=value
                    value = bufferReader.readLine()
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