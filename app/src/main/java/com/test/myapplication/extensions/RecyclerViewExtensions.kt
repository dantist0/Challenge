package com.test.myapplication.extensions

import androidx.annotation.IntRange
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */

fun RecyclerView.addLastItemReachListener(
    @IntRange(from = 1) lastItemsCount: Int,
    linearLayoutManager: LinearLayoutManager,
    listener: () -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val adapter = adapter ?: return
            val position = linearLayoutManager.findLastCompletelyVisibleItemPosition()
            if (position >= adapter.itemCount - lastItemsCount) {
                listener()
            }
        }
    })
}