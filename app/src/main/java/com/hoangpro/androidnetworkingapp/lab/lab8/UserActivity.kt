package com.hoangpro.androidnetworkingapp.lab.lab8

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val ref = database.reference.child("job")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setupView()
    }

    private fun setupView() {
        val user = firebaseAuth.currentUser?:return
        edtEmail.setText(user.email)
        btnChangeEmail.setOnClickListener {
            val credential  = EmailAuthProvider.getCredential(user.email?:"",edtPassword.text.toString())
            user.reauthenticate(credential).addOnSuccessListener {
                val user = FirebaseAuth.getInstance().currentUser?:return@addOnSuccessListener
                user.updateEmail(edtEmail.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this@UserActivity,"Cập nhật email thành công",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Lab8Task1Activity::class.java))
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this@UserActivity,it.message!!,Toast.LENGTH_SHORT).show()
                    }
            }
        }
        btnChangePassword.setOnClickListener {
            user.updatePassword(edtPassword.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this@UserActivity,"Cập nhật password thành công",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Lab8Task1Activity::class.java))
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@UserActivity,it.message!!,Toast.LENGTH_SHORT).show()
                }
        }
        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this,Lab8Task1Activity::class.java))
            finish()
        }

        btnAddJob.setOnClickListener {
            ref.push().setValue(edtJob.text.toString())
        }
        val adapter = TextAdapter(this)
        rvJob.adapter=adapter
        rvJob.layoutManager = LinearLayoutManager(this)
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList =ArrayList<String>()
                for(snap in snapshot.children){
                    dataList.add(snap.value.toString())
                }
                adapter.setData(dataList)
            }
        })
    }
}