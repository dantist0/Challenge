package com.test.myapplication.ui.fragments.home.adapter

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseViewHolder
import com.test.myapplication.databinding.ItemImageBinding
import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.utils.GlideListener

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val onItemClickListener: (ImageData) -> Unit
) : BaseViewHolder<ImageData>(binding.root) {

    override fun bind(data: ImageData) {
        binding.imageView.setOnClickListener { onItemClickListener(data) }
        binding.userInfo.userNameTextView.text = data.user
        binding.errorLoadingImageText.isVisible = false

        Glide.with(binding.userInfo.profileImageView)
            .load(data.userImageUrl)
            .placeholder(R.drawable.ic_user)
            .error(R.drawable.ic_user)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.userInfo.profileImageView)

        Glide.with(binding.imageView)
            .load(data.previewUrl)
            .listener(
                GlideListener(
                    onSuccess = { binding.errorLoadingImageText.isVisible = false },
                    onError = { binding.errorLoadingImageText.isVisible = true }
                )
            )
            .into(binding.imageView)
    }

    override fun recycle() {
        Glide.with(binding.imageView).clear(binding.imageView)
        Glide.with(binding.userInfo.profileImageView).clear(binding.userInfo.profileImageView)
    }
}