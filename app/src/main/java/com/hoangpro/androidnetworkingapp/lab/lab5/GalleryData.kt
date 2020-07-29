package com.hoangpro.androidnetworkingapp.lab.lab5

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class GalleryData (
    @SerializedName("galleries") val galleries: Galleries,
    @SerializedName("stat") val stat: String
) {
    companion object {
        fun create(json: String): GalleryData {
            val gson = GsonBuilder().create()
            return gson.fromJson(json, GalleryData::class.java)
        }
    }

    override fun toString(): String {
        val gson = GsonBuilder().create()
        return gson.toJson(this)
    }

    data class Galleries (
        @SerializedName("total") val total: Int,
        @SerializedName("per_page") val perPage: Int,
        @SerializedName("user_id") val userId: String,
        @SerializedName("continuation") val continuation: Int,
        @SerializedName("gallery") val gallery: List<Gallery>
    ) {
        companion object {
            fun create(json: String): Galleries {
                val gson = GsonBuilder().create()
                return gson.fromJson(json,Galleries::class.java)
            }
        }

        override fun toString(): String {
            val gson = GsonBuilder().create()
            return gson.toJson(this)
        }
    }
    data class Gallery (
        @SerializedName("id") val id: String,
        @SerializedName("gallery_id") val galleryId: String,
        @SerializedName("url") val url: String,
        @SerializedName("owner") val owner: String,
        @SerializedName("username") val username: String,
        @SerializedName("iconserver") val iconserver: String,
        @SerializedName("iconfarm") val iconfarm: Int,
        @SerializedName("primary_photo_id") val primaryPhotoId: String,
        @SerializedName("date_create") val dateCreate: String,
        @SerializedName("date_update") val dateUpdate: String,
        @SerializedName("count_photos") val countPhotos: Int,
        @SerializedName("count_videos") val countVideos: Int,
        @SerializedName("count_total") val countTotal: Int,
        @SerializedName("count_views") val countViews: Int,
        @SerializedName("count_comments") val countComments: Int,
        @SerializedName("title") val title: Title,
        @SerializedName("description") val description: Description,
        @SerializedName("sort_group") val sortGroup: Any,
        @SerializedName("primary_photo_server") val primaryPhotoServer: String,
        @SerializedName("primary_photo_farm") val primaryPhotoFarm: Int,
        @SerializedName("primary_photo_secret") val primaryPhotoSecret: String
    ) {
        companion object {
            fun create(json: String): Gallery {
                val gson = GsonBuilder().create()
                return gson.fromJson(json,Gallery::class.java)
            }
        }

        override fun toString(): String {
            val gson = GsonBuilder().create()
            return gson.toJson(this)
        }
    }

    data class Title (
        @SerializedName("_content") val Content: String
    ) {
        companion object {
            fun create(json: String): Title {
                val gson = GsonBuilder().create()
                return gson.fromJson(json, Title::class.java)
            }
        }

        override fun toString(): String {
            val gson = GsonBuilder().create()
            return gson.toJson(this)
        }
    }

    data class Description (
        @SerializedName("_content") val Content: String
    ) {
        companion object {
            fun create(json: String): Description {
                val gson = GsonBuilder().create()
                return gson.fromJson(json, Description::class.java)
            }
        }

        override fun toString(): String {
            val gson = GsonBuilder().create()
            return gson.toJson(this)
        }
    }
}