package com.hoangpro.androidnetworkingapp.lab.lab8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.item_text.view.*

class TextAdapter(context: Context) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    private val context = context
    private val dataList = ArrayList<String>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvValue= itemView.tvValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text,parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: ArrayList<String>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvValue.text = dataList[position]
    }
}