package com.hoangpro.androidnetworkingapp.lab.lab2

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_lab2_task2.btnSubmit
import kotlinx.android.synthetic.main.activity_lab2_task4.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL

class Lab2Task4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2_task4)
        btnSubmit.setOnClickListener {
            val loading = DialogHelper.showDialogLoading(this)
            val handlePTB2 = HandlePTB2("${Lab2Activity.url}/ptb2") {
                tvResult.text = it
                loading.dismiss()
            }
            handlePTB2.execute(
                edtA.text.toString().toFloat(),
                edtB.text.toString().toFloat(),
                edtC.text.toString().toFloat()
            )
        }
    }

    inner class HandlePTB2(link: String, onResult: (result: String) -> Unit) :
        AsyncTask<Float, Void, String>() {
        private val link = link
        private val onResult = onResult
        override fun doInBackground(vararg params: Float?): String {
            var result = ""
            try {
                val url = URL(link)
                val connection = url.openConnection() as HttpURLConnection
                val body = "a=${params[0]}&b=${params[1]}&c=${params[2]}"
                connection.apply {
                    doOutput = true
                    setFixedLengthStreamingMode(body.toByteArray().size)
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                }
                val printWriter = PrintWriter(connection.outputStream)
                printWriter.print(body)
                printWriter.close()

                val bufferReadable = BufferedReader(InputStreamReader(connection.inputStream))
                var value = bufferReadable.readLine()
                while (value != null) {
                    result += value
                    value = bufferReadable.readLine()
                }
                connection.disconnect()
            } catch (e: Exception) {
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