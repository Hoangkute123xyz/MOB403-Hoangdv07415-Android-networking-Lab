package com.hoangpro.androidnetworkingapp.lab.lab2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_lab2_task1.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Lab2Task1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2_task1)
        btnSubmit.setOnClickListener {
            val loading = DialogHelper.showDialogLoading(this)
            val handleGetData = HandleGetData(Lab2Activity.url){
                tvReuslt.text=it
                loading.dismiss()
            }
            handleGetData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,edtName.text.toString(),edtScore.text.toString())
        }
    }

    inner class HandleGetData(url:String,onResult:(value:String)->Unit):AsyncTask<String,Void,String>(){
        private val url = url
        private val onResult = onResult
        override fun onPostExecute(result: String?) {
            onResult(result?:"Error")
        }

        override fun doInBackground(vararg params: String?): String {
            var result =""
            try {
                val data = "$url/user?name=${params[0]}&score=${params[1]}"
                val url = URL(data)
                val connection = url.openConnection() as HttpURLConnection
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
    }
}