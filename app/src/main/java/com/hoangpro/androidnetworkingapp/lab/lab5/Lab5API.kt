package com.hoangpro.androidnetworkingapp.lab.lab5

import com.google.gson.JsonElement
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Lab5API{
    @GET("/services/rest/?method=flickr.galleries.getList&api_key=5b0aa9e20237e1bfcb385938bcf5d5cf&user_id=186435189%40N06&continuation=0&format=json&nojsoncallback=1")
    fun getGallery(): Call<GalleryData>

    @GET("/services/rest/?method=flickr.galleries.getPhotos&api_key=5b0aa9e20237e1bfcb385938bcf5d5cf&continuation=0&per_page=20&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&format=json&nojsoncallback=1")
    fun getPhotosFromGallery(@Query("gallery_id") galleryId:String, @Query("page") page:String):Call<JsonElement>
}