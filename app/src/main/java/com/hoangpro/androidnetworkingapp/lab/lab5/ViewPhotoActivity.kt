package com.hoangpro.androidnetworkingapp.lab.lab5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoAdapter
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoData
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.activity_view_photo.*

class ViewPhotoActivity : AppCompatActivity() {
    private lateinit var photoAdapter: PhotoAdapter
    private var currentPage = 1
    var galleryId = ""
    private var photoData: PhotoData? = null
    private var canLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_photo)
        galleryId = intent.getStringExtra("galleryId") ?: ""
        setupView()
        initData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initData() {
        currentPage=1
        pgLoading.isRefreshing=true
        Lab5Client.getPhotosFromGallery(
            galleryId,
            currentPage,
            onSuccess = {
                if (it != null) {
                    photoData = it
                    photoAdapter.setData(it)
                }
                pgLoading.isRefreshing=false
            },
            onFailure = {
                pgLoading.isRefreshing=true
            }
        )
    }

    private fun setupView() {
        photoAdapter = PhotoAdapter(this)
        rvPhoto.apply {
            adapter = photoAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        rvPhoto.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (photoData == null) return
                var arr = IntArray(2) { 0 }
                val manager = rvPhoto.layoutManager as StaggeredGridLayoutManager
                manager.findLastVisibleItemPositions(arr)
                if (arr[1] == photoData!!.photos!!.photo!!.size - 2 && canLoadMore && currentPage < photoData!!.photos!!.pages!!) {
                    moveToNextPage()
                }
            }
        })
        pgLoading.setOnRefreshListener {
            initData()
        }
    }


    private fun moveToNextPage() {
        canLoadMore = false
        pgLoading.isRefreshing = true
        currentPage++
        Lab5Client.getPhotosFromGallery(
            galleryId,
            currentPage,
            onSuccess = {
                if (it != null) {
                    photoAdapter.addData(it)
                }
                pgLoading.isRefreshing = false
                canLoadMore = true
            },
            onFailure = {
                pgLoading.isRefreshing = false
                canLoadMore = true
            }
        )
    }
}