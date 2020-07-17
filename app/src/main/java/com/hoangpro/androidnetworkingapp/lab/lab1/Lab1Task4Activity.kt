package com.hoangpro.androidnetworkingapp.lab.lab1

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab1_task4.*
import kotlin.math.abs

class Lab1Task4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task4)
        edtTime.setText("0")
        btnOk.setOnClickListener {
            val time = edtTime.text.toString().toInt()
            val builder = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_handle_sleep, null, false)
            val tvTimeCount = view.findViewById<TextView>(R.id.tvTimeCount)
            builder.setView(view)
            val alertDialog = builder.create()
            alertDialog.setOnShowListener {
                val handleSleepAsync = HandleSleepAsync(
                    onUpdate = {
                        tvTimeCount.text = (time - it).toString()
                    },
                    onDone = {
                        Handler().postDelayed(Runnable {
                            alertDialog.dismiss()
                        }, 100)
                    }
                )
                handleSleepAsync.execute(time)
            }
            if (time > 0) {
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }

    inner class HandleSleepAsync(onUpdate: (value: Int) -> Unit, onDone: () -> Unit) :
        AsyncTask<Int, Long, Boolean>() {
        private val onUpdate = onUpdate
        private val onDone = onDone
        override fun onProgressUpdate(vararg values: Long?) {
            val currentTime = values[0]
            val value = (currentTime!! / 1000L).toInt()
            onUpdate(value)
            super.onProgressUpdate(*values)
        }

        override fun doInBackground(vararg params: Int?): Boolean {
            val time = abs(params[0]!!.toLong()) * 1000L
            val startTime = System.currentTimeMillis()
            var currentTime = System.currentTimeMillis() - startTime
            while (currentTime <= time) {
                if (currentTime % 1000L <= 10) {
                    publishProgress(currentTime)
                }
                currentTime = System.currentTimeMillis() - startTime
            }
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            onDone()
            super.onPostExecute(result)
        }

    }
}