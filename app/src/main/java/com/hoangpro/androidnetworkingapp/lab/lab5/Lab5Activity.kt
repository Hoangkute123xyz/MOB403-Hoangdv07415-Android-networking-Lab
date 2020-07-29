package com.hoangpro.androidnetworkingapp.lab.lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab5.*

class Lab5Activity : AppCompatActivity() {
    private lateinit var galleryAdapter: GalleryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab5)
        setupView()
        initData()
    }

    private fun setupView() {
        galleryAdapter = GalleryAdapter(this){
            val intent = Intent(this,ViewPhotoActivity::class.java)
            intent.putExtra("galleryId",it)
            startActivity(intent)
        }
        rvCategory.apply {
            adapter=galleryAdapter
            layoutManager = LinearLayoutManager(this@Lab5Activity)
        }
        pgLoading.setOnRefreshListener {
            initData()
        }
    }

    private fun initData(){
        pgLoading.isRefreshing=true
        Lab5Client.getAllGallery(
            onSuccess = {
                if (it!=null)
                galleryAdapter.setData(it.galleries)
                pgLoading.isRefreshing=false
            },
            onFailure = {
                pgLoading.isRefreshing=false
            }
        )
    }
}