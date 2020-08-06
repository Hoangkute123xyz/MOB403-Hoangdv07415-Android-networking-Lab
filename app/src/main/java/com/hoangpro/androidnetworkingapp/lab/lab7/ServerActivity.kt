package com.hoangpro.androidnetworkingapp.lab.lab7

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_server.*
import java.io.BufferedReader
import java.io.DataInputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.jar.JarInputStream

class ServerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)
        val handleServer = HandleServer{
            tvResult.text = it
            val handleServer = HandleServer{res->
                tvResult.text = res
            }
            handleServer.execute()
        }
        handleServer.execute()
    }

    class HandleServer(onResult:(res:String)->Unit) : AsyncTask<Void,String,Boolean>(){
        private val onResult = onResult
        companion object{
            val PORT = 9754
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            try {
                while (true){
                    val severSocket = ServerSocket(PORT)
                    var socket = severSocket.accept()
                    Log.e("server","running")
                    var dataInputStream = DataInputStream(socket.getInputStream())
                    val message = dataInputStream.readUTF()
                    dataInputStream.close()
                    if (message.isNotEmpty()){
                        socket.close()
                        severSocket.close()
                        publishProgress(message)
                    }
                }
            } catch (e:Exception){
                e.printStackTrace()
                Log.e("server","stopped")
            }
            return true
        }


        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            onResult(values[0]?:"")
        }

    }
}