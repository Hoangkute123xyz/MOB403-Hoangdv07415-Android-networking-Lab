package com.hoangpro.androidnetworkingapp.lab.lab7

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_client.*
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        btnSend.setOnClickListener {
            val handleClient = HandleClient()
            handleClient.execute(edtMessage.text.toString())
        }
    }

    class HandleClient : AsyncTask<String,Void,Boolean>(){
        companion object{
            val IP = "192.168.100.102"
        }
        override fun doInBackground(vararg params: String?): Boolean {
            try {
                val socket = Socket(IP,ServerActivity.HandleServer.PORT)
                val dataOutputStream = DataOutputStream(socket.getOutputStream())
                dataOutputStream.writeUTF(params[0]!!)
                dataOutputStream.flush()
                dataOutputStream.close()
            } catch (e:Exception){
                e.printStackTrace()
            }
            return true
        }
    }
}