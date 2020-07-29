package com.hoangpro.androidnetworkingapp.lab.lab5

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Lab5Client {
    companion object{
        private val API by lazy { create() }
        private fun create():Lab5API{
            val builder = Retrofit.Builder().baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder.create(Lab5API::class.java)
        }


        fun getAllGallery(onSuccess:(result:GalleryData?)->Unit,onFailure:()->Unit){
            API.getGallery().enqueue(object : Callback<GalleryData>{
                override fun onFailure(call: Call<GalleryData>, t: Throwable) {
                    onFailure()
                }

                override fun onResponse(call: Call<GalleryData>, response: Response<GalleryData>) {
                    if (response.isSuccessful){
                        onSuccess(response.body())
                    } else {
                        onFailure()
                    }
                }
            })
        }
        fun getPhotosFromGallery(galleryId:String,page:Int,onSuccess: (result: PhotoData?) -> Unit,onFailure: () -> Unit){
            API.getPhotosFromGallery(galleryId,page.toString()).enqueue(object :Callback<JsonElement>{
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    onFailure()
                }

                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if (response.isSuccessful){
                        val json = response.body().toString()
                        val photoData = Gson().fromJson(json,PhotoData::class.java)
                        Log.e("jsonData",photoData.photos!!.pages.toString())
                        onSuccess(photoData)
                    } else {
                        onFailure()
                    }
                }
            })
        }
    }
}