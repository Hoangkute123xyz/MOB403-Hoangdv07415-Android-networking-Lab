package com.hoangpro.androidnetworkingapp.lab.lab3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(context: Context,listPost:ArrayList<PostData.Post>,postDetailList:ArrayList<PostData.PostDetail>,onItemClicked:(position:Int)->Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val onItemClicked=onItemClicked
    private val context = context
    private val listPost = listPost
    private val postDetailList=postDetailList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.tvTitle
        val imgThumb = itemView.imgThumb
        val tvTime = itemView.tvTime
        val tvDescription = itemView.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int =listPost.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listPost[position]
        val posDetail = postDetailList[position]
        holder.tvTitle.text = post.title
        holder.tvTime.text = posDetail.date
        holder.tvDescription.text=HtmlCompat.fromHtml(posDetail.description,HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
        try {
            val url = posDetail.content.split("<img src=\"")[1].split("\"")[0]
            Glide.with(context).load(url).into(holder.imgThumb)
        } catch (e:Exception){
            e.printStackTrace()
        }
    }
}