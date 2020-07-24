package com.hoangpro.androidnetworkingapp.lab.lab4

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.activity_lab4.*
import okhttp3.OkHttpClient

class Lab4Activity : AppCompatActivity() {
    private lateinit var photoAdapter :PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab4)
        photoAdapter= PhotoAdapter(this)
        rvPhoto.apply {
            adapter=photoAdapter
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        fetchData()
        swRefresh.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData(){
        swRefresh.isRefreshing=true
        PhotoClient.getFavoritesPhotos(onSuccess = {
            if (it!=null)
            photoAdapter.setData(it)
            swRefresh.isRefreshing=false
        },onFailure = {

        })
    }
}