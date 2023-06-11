package com.test.myapplication.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseFragment
import com.test.myapplication.databinding.FragmentDetailsBinding
import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.utils.GlideListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class DetailsFragment : BaseFragment() {
    private val imageId by lazy { requireArguments().getLong(IMAGE_ID) }

    private var binding: FragmentDetailsBinding? = null

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = checkNotNull(binding)

        viewModel.load(imageId)

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
            binding.mainContentContainer.isVisible = !it
        }
        viewModel.showErrorLeveData.observe(viewLifecycleOwner) {
            binding.errorTextView.isVisible = it != null
            binding.errorTextView.text =
                getString(R.string.loading_error_with_message, it?.message.orEmpty())
        }
        viewModel.imageLiveData.observe(viewLifecycleOwner) {
            showData(it)
        }
    }

    private fun showData(imageData: ImageData) {
        val binding = requireNotNull(binding)

        Glide.with(requireContext())
            .load(imageData.largeImageUrl)
            .addListener(
                GlideListener(
                    onSuccess = { binding.imageProgressBar.isGone = true },
                    onError = {
                        binding.errorLoadingImageText.isVisible = true
                        binding.imageProgressBar.isGone = true
                    }
                )
            )
            .into(binding.imageView)

        Glide.with(requireContext())
            .load(imageData.userImageUrl)
            .placeholder(R.drawable.ic_user)
            .error(R.drawable.ic_user)
            .into(binding.userInfo.profileImageView)

        binding.userInfo.userNameTextView.text = imageData.user

        binding.imageInfoTextView.text = buildImageInfoString(imageData)
        binding.viewsTextView.text = imageData.views.toString()
        binding.likesTextView.text = imageData.likes.toString()
        binding.commentsTextView.text = imageData.comments.toString()
        binding.downloadsTextView.text = imageData.downloads.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun buildImageInfoString(imageData: ImageData) =
        """
           |size: ${imageData.imageWidth}x${imageData.imageHeight}
           |type: ${imageData.type}
           |tags: ${imageData.tags}
           """.trimMargin()

    companion object {
        private const val IMAGE_ID = "IMAGE_ID"

        fun newInstance(imageId: Long) = DetailsFragment().apply {
            arguments = Bundle().apply { putLong(IMAGE_ID, imageId) }
        }
    }
}