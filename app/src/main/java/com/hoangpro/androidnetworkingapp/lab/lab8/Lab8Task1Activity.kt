package com.hoangpro.androidnetworkingapp.lab.lab8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab8_task1.*

class Lab8Task1Activity : AppCompatActivity() {
    private val firebaseAuth =FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab8_task1)

        setupView()
    }

    private fun setupView() {
        btnRegister.setOnClickListener {
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.text.toString(),edtPassword.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful)
                    firebaseAuth.currentUser!!.sendEmailVerification().addOnSuccessListener {
                        Snackbar.make(tvForgotPassword,"Dang ky thanh cong vui long xac thuc email cua ban!!",Snackbar.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Snackbar.make(tvForgotPassword,"Khong the dang ky voi email nay!!",Snackbar.LENGTH_SHORT).show()
                }
        }
        btnLogin.setOnClickListener {
            firebaseAuth.signInWithEmailAndPassword(edtEmail.text.toString(),edtPassword.text.toString())
                .addOnSuccessListener {
                    startActivity(Intent(this,UserActivity::class.java))
                    Toast.makeText(this@Lab8Task1Activity,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@Lab8Task1Activity,"Sai email hoac mat khau",Toast.LENGTH_SHORT).show()
                }
        }
        tvForgotPassword.setOnClickListener {
            firebaseAuth.sendPasswordResetEmail(edtEmail.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this@Lab8Task1Activity,"Mot email reset mật khẩu dã được gửi",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this@Lab8Task1Activity,"${it.message}",Toast.LENGTH_SHORT).show()
                }
        }
    }
}