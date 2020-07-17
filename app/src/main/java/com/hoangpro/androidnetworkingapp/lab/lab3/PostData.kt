package com.hoangpro.androidnetworkingapp.lab.lab3

import org.json.JSONArray
import org.json.JSONObject

class PostData(jsonArray: JSONArray) {
    private val jsonArray = jsonArray

    val postList: ArrayList<Post>
        get() {
            val postList = ArrayList<Post>()
            for (i in 0 until jsonArray.length()) {
                postList.add(Post(jsonArray.getJSONObject(i)))
            }
            return postList
        }

    inner class Post(jsonObject: JSONObject) {
        private val jsonObject = jsonObject
        val id: Int
            get() = jsonObject.getInt("id")
        val title: String
            get() = jsonObject.getString("title")
        val type: String
            get() = jsonObject.getString("post")
        val subtype: String
            get() = jsonObject.getString("subtype")

        val self: Self
            get() = Self(jsonObject.getJSONObject("_links").getJSONArray("self").getJSONObject(0))
        val about: Href
            get() = Href(jsonObject.getJSONObject("_links").getJSONArray("about").getJSONObject(0))
        val collection: Href
            get() = Href(
                jsonObject.getJSONObject("_links").getJSONArray("collection").getJSONObject(0)
            )
    }

    inner class Self(jsonObject: JSONObject) {
        private val jsonObject = jsonObject

        val embeddable: Boolean
            get() = jsonObject.getBoolean("embeddable")
        val href: String
            get() = jsonObject.getString("href")
    }

    inner class Href(jsonObject: JSONObject) {
        private val jsonObject = jsonObject
        val href: String
            get() = jsonObject.getString("href")
    }

    class PostDetail(json: JSONObject) {
        private val json = json
        val title: String
            get() = json.getJSONObject("title").getString("rendered")
        val content: String
            get() = json.getJSONObject("content").getString("rendered")
        val date
            get() = json.getString("date")
        val description: String
            get() = json.getJSONObject("excerpt").getString("rendered")
    }
}