package com.test.myapplication.ui.fragments.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseViewHolder
import com.test.myapplication.extensions.inflater
import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.ui.fragments.home.models.LoadingData

private const val VIEW_TYPE_IMAGE = 0
private const val VIEW_TYPE_LOADING = 1

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class ImagesListAdapter(
    private val onItemClickListener: (itemData: ImageData) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var itemsData = listOf<ImageData>()
    private var loadingData = LoadingData(isLoading = false, error = null)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItemsData: List<ImageData>) {
        this.itemsData = newItemsData
        notifyDataSetChanged()
    }

    fun setLoading(isLoading: Boolean) {
        loadingData = loadingData.copy(isLoading = isLoading)
        notifyItemChanged(itemsData.size)
    }

    fun setError(error: Throwable?) {
        loadingData = loadingData.copy(error = error)
        notifyItemChanged(itemsData.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < itemsData.size) VIEW_TYPE_IMAGE else VIEW_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_IMAGE -> ImageViewHolder(
                DataBindingUtil.inflate(parent.inflater, R.layout.item_image, parent, false),
                onItemClickListener
            )

            VIEW_TYPE_LOADING -> LoadingViewHolder(
                DataBindingUtil.inflate(parent.inflater, R.layout.item_loading, parent, false)
            )

            else -> error("Unknown viewType = $viewType")
        }
    }

    override fun getItemCount(): Int {
        return itemsData.size + 1
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(itemsData[position])
            is LoadingViewHolder -> holder.bind(loadingData)
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder<*>) {
        holder.recycle()
    }
}

