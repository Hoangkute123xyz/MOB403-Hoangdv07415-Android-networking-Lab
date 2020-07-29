package com.hoangpro.androidnetworkingapp.lab.lab5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(context: Context,onItemClick:(galleryId:String)->Unit) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private val onItemClick=onItemClick
    private val context=context
    private var galleries : GalleryData.Galleries?=null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gallery,parent,false))
    }

    override fun getItemCount(): Int = if (galleries!=null) galleries!!.gallery.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gallery = galleries!!.gallery[position]
        holder.tvName.text=gallery.title.Content
        holder.itemView.setOnClickListener {
            onItemClick(gallery.description.Content)
        }
    }
    fun setData(galleries: GalleryData.Galleries){
        this.galleries=galleries
        notifyDataSetChanged()
    }
}