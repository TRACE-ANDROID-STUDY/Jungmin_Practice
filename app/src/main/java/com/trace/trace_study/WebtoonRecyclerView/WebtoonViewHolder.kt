package com.trace.trace_study.WebtoonRecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trace.trace_study.InstaRecyclerView.InstaData
import com.trace.trace_study.R

class WebtoonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_name = itemView.findViewById<TextView>(R.id.textView5)
    val img_main = itemView.findViewById<ImageView>(R.id.img_main)
    val tv_when = itemView.findViewById<TextView>(R.id.tv_when)

    fun bind(webtoonData: WebtoonData){
        tv_name.text=webtoonData.toonname
        tv_when.text=webtoonData.toonwhen
        Glide.with(itemView).load(webtoonData.img_main).into(img_main)
    }

}