package com.hoangpro.androidnetworkingapp.lab.lab4

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PhotoData {
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null

    @SerializedName("stat")
    @Expose
    var stat: String? = null

    class Photo {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("owner")
        @Expose
        var owner: String? = null

        @SerializedName("secret")
        @Expose
        var secret: String? = null

        @SerializedName("server")
        @Expose
        var server: String? = null

        @SerializedName("farm")
        @Expose
        var farm: Int? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("ispublic")
        @Expose
        var ispublic: Int? = null

        @SerializedName("isfriend")
        @Expose
        var isfriend: Int? = null

        @SerializedName("isfamily")
        @Expose
        var isfamily: Int? = null

        @SerializedName("views")
        @Expose
        var views: String? = null

        @SerializedName("date_faved")
        @Expose
        var dateFaved: String? = null

        @SerializedName("media")
        @Expose
        var media: String? = null

        @SerializedName("media_status")
        @Expose
        var mediaStatus: String? = null

        @SerializedName("url_sq")
        @Expose
        var urlSq: String? = null

        @SerializedName("height_sq")
        @Expose
        var heightSq: Int? = null

        @SerializedName("width_sq")
        @Expose
        var widthSq: Int? = null

        @SerializedName("url_t")
        @Expose
        var urlT: String? = null

        @SerializedName("height_t")
        @Expose
        var heightT: Int? = null

        @SerializedName("width_t")
        @Expose
        var widthT: Int? = null

        @SerializedName("url_s")
        @Expose
        var urlS: String? = null

        @SerializedName("height_s")
        @Expose
        var heightS: Int? = null

        @SerializedName("width_s")
        @Expose
        var widthS: Int? = null

        @SerializedName("url_q")
        @Expose
        var urlQ: String? = null

        @SerializedName("height_q")
        @Expose
        var heightQ: Int? = null

        @SerializedName("width_q")
        @Expose
        var widthQ: Int? = null

        @SerializedName("url_m")
        @Expose
        var urlM: String? = null

        @SerializedName("height_m")
        @Expose
        var heightM: Int? = null

        @SerializedName("width_m")
        @Expose
        var widthM: Int? = null

        @SerializedName("url_n")
        @Expose
        var urlN: String? = null

        @SerializedName("height_n")
        @Expose
        var heightN: Int? = null

        @SerializedName("width_n")
        @Expose
        var widthN: Int? = null

        @SerializedName("url_z")
        @Expose
        var urlZ: String? = null

        @SerializedName("height_z")
        @Expose
        var heightZ: Int? = null

        @SerializedName("width_z")
        @Expose
        var widthZ: Int? = null

        @SerializedName("url_c")
        @Expose
        var urlC: String? = null

        @SerializedName("height_c")
        @Expose
        var heightC: Int? = null

        @SerializedName("width_c")
        @Expose
        var widthC: Int? = null

        @SerializedName("url_l")
        @Expose
        var urlL: String? = null

        @SerializedName("height_l")
        @Expose
        var heightL: Int? = null

        @SerializedName("width_l")
        @Expose
        var widthL: Int? = null

        @SerializedName("pathalias")
        @Expose
        var pathalias: Any? = null

    }

    class Photos {
        @SerializedName("page")
        @Expose
        var page: Int? = null

        @SerializedName("pages")
        @Expose
        var pages: Int? = null

        @SerializedName("perpage")
        @Expose
        var perpage: Int? = null

        @SerializedName("total")
        @Expose
        var total: String? = null

        @SerializedName("photo")
        @Expose
        var photo: ArrayList<Photo>? =
            null

    }
}