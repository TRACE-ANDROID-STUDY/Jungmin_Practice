package com.trace.trace_study.InstaRecyclerView

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Itemdecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = mSpace
        outRect.left= mSpace
        outRect.right= mSpace
        outRect.top= mSpace
    }

}