package com.hoangpro.androidnetworkingapp.lab.lab4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotoClient {
    companion object {
        private val API_KEY = "5b0aa9e20237e1bfcb385938bcf5d5cf"
        private val USER_ID = "186435189@N06"
        private val EXTRAS =
            "views, media, path_alias, url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"
        private val GET_FAVORITE_METHOD = "flickr.favorites.getList"
        private val JSON_FORMAT = "json"
        private val API by lazy { create() }
        private fun create(): PhotoAPI {
            val retrofit = Retrofit.Builder().baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PhotoAPI::class.java)
        }

        fun getFavoritesPhotos(
            onSuccess: (result: PhotoData?) -> Unit,
            onFailure: (message: String) -> Unit
        ) {
            API.getPhotoFavorites().enqueue(object : Callback<PhotoData> {
                override fun onFailure(call: Call<PhotoData>, t: Throwable) {
                    onFailure(t.message!!)
                }

                override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                    if (response.isSuccessful) {
                        onSuccess(response.body())
                    } else {
                        onFailure("error Convert")
                    }
                }

            })
        }
    }

}