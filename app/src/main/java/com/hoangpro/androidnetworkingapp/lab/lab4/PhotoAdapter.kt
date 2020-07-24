package com.hoangpro.androidnetworkingapp.lab.lab4

import android.app.AlertDialog
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val context = context
    private var photoData :PhotoData?=null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgData = itemView.imgData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo,parent,false))
    }

    override fun getItemCount(): Int {
        return if (photoData!=null) photoData!!.photos!!.photo!!.size else 0
    }

    fun setData(photoData: PhotoData){
        this.photoData = photoData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photoData!!.photos!!.photo?.get(position)!!
        Glide.with(context).load(photo.urlS).into(holder.imgData)
        holder.itemView.setOnClickListener {
            showDialogDetail(photo)
        }
    }

    private fun showDialogDetail(photo:PhotoData.Photo){
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_view_photo,null,false)
        builder.setView(view)
        val imgData = view.findViewById<ImageView>(R.id.imgData)
        val tvSize = view.findViewById<TextView>(R.id.tvSize)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        Glide.with(context).load(photo.urlM).into(imgData)
        tvSize.text = "${photo.widthM} x ${photo.heightM}"
        tvName.text = photo.title
        builder.create().show()
    }
}