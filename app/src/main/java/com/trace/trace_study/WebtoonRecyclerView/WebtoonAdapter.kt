package com.trace.trace_study.WebtoonRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trace.trace_study.InstaRecyclerView.InstaData
import com.trace.trace_study.InstaRecyclerView.InstaViewHolder
import com.trace.trace_study.R


class WebtoonAdapter(private val context: Context) : RecyclerView.Adapter<WebtoonViewHolder>(){
    var datas= mutableListOf<WebtoonData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebtoonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_webtoon,parent,false)
        return WebtoonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: WebtoonViewHolder, position: Int) {
        holder.bind(datas[position])
    }


}