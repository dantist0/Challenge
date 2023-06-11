package com.test.myapplication.base.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
abstract class BaseViewHolder<T: Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
    abstract fun recycle()
}