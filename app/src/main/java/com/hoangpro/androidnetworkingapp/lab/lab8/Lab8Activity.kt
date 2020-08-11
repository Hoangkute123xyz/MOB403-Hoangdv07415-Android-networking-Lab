package com.hoangpro.androidnetworkingapp.lab.lab8

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab8.*
import java.io.IOException
import java.util.*


class Lab8Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab8)

        btnTask1.setOnClickListener {
            startActivity(Intent(this,Lab8Task1Activity::class.java))
        }
        btnTask3.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                1002
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1002 && resultCode === Activity.RESULT_OK && data != null && data.data != null
        ) {
           val filePath = data.data
            try {
                val storage = FirebaseStorage.getInstance()
                val storageReference = storage.reference
                if (filePath != null) {
                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("Uploading...")
                    progressDialog.show()
                    val ref =
                        storageReference.child("images/" + UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .addOnFailureListener { e ->
                            progressDialog.dismiss()
                            Toast.makeText(
                                this,
                                "Failed " + e.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnProgressListener { taskSnapshot ->
                            val progress =
                                100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                                    .totalByteCount
                            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                        }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}