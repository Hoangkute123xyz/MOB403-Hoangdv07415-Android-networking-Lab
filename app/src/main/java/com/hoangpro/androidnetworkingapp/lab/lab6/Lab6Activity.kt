package com.hoangpro.androidnetworkingapp.lab.lab6

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_lab6.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request


class Lab6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab6)

        btnCtoF.setOnClickListener {
            val dialog = DialogHelper.showDialogLoading(this@Lab6Activity)
            HandleSoap(HandleSoap.C_TO_F_METHOD_NAME, "Celsius") {
                dialog.dismiss()
                tvResult.text = "C to F has result : $it"

            }.execute(edtValue.text.toString())
        }
        btnFtoC.setOnClickListener {
            val dialog = DialogHelper.showDialogLoading(this@Lab6Activity)
            HandleSoap(HandleSoap.F_TO_C_METHOD_NAME, "Fahrenheit") {
                dialog.dismiss()
                tvResult.text = "F to C has result : $it"

            }.execute(edtValue.text.toString())
        }
    }

    class HandleSoap(method: String, param: String, onResponse: (result: String) -> Unit) :
        AsyncTask<String, Void, String>() {
        val method = method
        val param = param
        val onResponse = onResponse

        companion object {
            val NAME_SPACE = "https://www.w3schools.com/xml/"
            val SOAP_ACTION = "https://www.w3schools.com/xml/"
            val URL = "https://www.w3schools.com/xml/tempconvert.asmx/"
            val F_TO_C_METHOD_NAME = "FahrenheitToCelsius"
            val C_TO_F_METHOD_NAME = "CelsiusToFahrenheit"
        }

        override fun doInBackground(vararg params: String?): String {
            try {
                val client = OkHttpClient()
                val body = FormBody.Builder().add(param, params[0]).build()
                val request = Request.Builder().url(URL + method).post(body).build()
                val response = client.newCall(request).execute()
                return if (response.isSuccessful) {
                    val stream = response.body()!!.charStream()
                    stream.readText().split("<string xmlns=\"https://www.w3schools.com/xml/\">")[1].split("</string>")[0]
                } else {
                    Log.e("errorPost", "error $response")
                    ""
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            onResponse(result!!)
        }
    }
}