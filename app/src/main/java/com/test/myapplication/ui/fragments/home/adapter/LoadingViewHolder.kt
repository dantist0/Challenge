package com.test.myapplication.ui.fragments.home.adapter

import com.test.myapplication.base.ui.BaseViewHolder
import com.test.myapplication.databinding.ItemLoadingBinding
import com.test.myapplication.ui.fragments.home.models.LoadingData

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class LoadingViewHolder(
    private val binding: ItemLoadingBinding
) : BaseViewHolder<LoadingData>(binding.root) {
    override fun bind(data: LoadingData) {
        binding.loadingData = data
    }

    override fun recycle() {
        //pass
    }
}