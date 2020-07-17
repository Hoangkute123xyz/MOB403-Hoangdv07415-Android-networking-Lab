package com.hoangpro.androidnetworkingapp.lab.lab3

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import com.hoangpro.androidnetworkingapp.util.StringHelper
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class PostActivity : AppCompatActivity() {

    private lateinit var  postAdapter :PostAdapter
    private val postList=ArrayList<PostData.Post>()
    private val postDetailList=ArrayList<PostData.PostDetail>()
    private val viewPostBSDF = ViewPostBSDF()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        postAdapter = PostAdapter(this,postList,postDetailList){
            viewPostBSDF.postDetail = postDetailList[it]
            viewPostBSDF.show(supportFragmentManager,viewPostBSDF.tag)
        }
        rvPost.apply {
            adapter=postAdapter
            layoutManager = LinearLayoutManager(this@PostActivity)
        }
        imgSearch.setOnClickListener {
            val dialog =DialogHelper.showDialogLoading(this)
            val query = StringHelper.toUrlEncode(edtQuery.text.toString())
            if (query.isNotEmpty()){
                val handlePost = HandlePost{postList,postDetailList->
                    for (post in postDetailList){
                        this.postList.clear()
                        this.postDetailList.clear()
                        this.postList.addAll(postList)
                        this.postDetailList.addAll(postDetailList)
                        postAdapter.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                }
                handlePost.execute(query)
            }
        }
    }

    inner class HandlePost(onResult:(postList:ArrayList<PostData.Post>,postDetailList: ArrayList<PostData.PostDetail>)->Unit):
        AsyncTask<String, Void, ArrayList<PostData.Post>>() {

        private val onResult = onResult
        private val postDetailList = ArrayList<PostData.PostDetail>()
        override fun doInBackground(vararg params: String?): ArrayList<PostData.Post> {
            try {
                var json=""
                val url = URL("http://dotplays.com/wp-json/wp/v2/search?search=${params[0]}&_embed")
                val connection = url.openConnection() as HttpURLConnection
                val bufferedReader=BufferedReader(InputStreamReader(connection.inputStream))
                var line = bufferedReader.readLine()
                while (line!=null){
                    json+=line
                    line = bufferedReader.readLine()
                }
                connection.disconnect()
                val postData = PostData(JSONArray(json))
                for (post in postData.postList){
                    var dataJson =""
                    val url = URL(post.self.href)
                    val connection = url.openConnection() as HttpURLConnection
                    val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                    var line = bufferedReader.readLine()
                    while (line!=null){
                        dataJson+=line
                        line = bufferedReader.readLine()
                    }
                    connection.disconnect()
                    postDetailList.add(PostData.PostDetail(JSONObject(dataJson)))
                }
                return postData.postList
            } catch (e:Exception){
                e.printStackTrace()
            }
            return arrayListOf()
        }

        override fun onPostExecute(result: ArrayList<PostData.Post>?) {
            onResult(result!!,postDetailList)
            super.onPostExecute(result)
        }

    }


}