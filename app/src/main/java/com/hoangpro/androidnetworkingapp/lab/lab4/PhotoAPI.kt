package com.hoangpro.androidnetworkingapp.lab.lab4

import retrofit2.Call
import retrofit2.http.*

interface PhotoAPI {
    @GET("services/rest/?api_key=5b0aa9e20237e1bfcb385938bcf5d5cf&user_id=186435189@N06&extras=views, media, path_alias, url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o&method=flickr.favorites.getList&format=json&nojsoncallback=1")
    fun getPhotoFavorites(): Call<PhotoData>
}