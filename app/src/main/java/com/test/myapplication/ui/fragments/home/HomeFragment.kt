package com.test.myapplication.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseFragment
import com.test.myapplication.databinding.FragmentHomeBinding
import com.test.myapplication.extensions.addLastItemReachListener
import com.test.myapplication.ui.fragments.details.DetailsFragment
import com.test.myapplication.ui.fragments.home.adapter.ImagesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = checkNotNull(binding)

        val imagesListAdapter = ImagesListAdapter(
            onItemClickListener = { navigateToDetailsFragment(it.id) }
        )
        val layoutManager = LinearLayoutManager(context)
        binding.imagesRecyclerView.adapter = imagesListAdapter
        binding.imagesRecyclerView.layoutManager = layoutManager
        binding.imagesRecyclerView.addLastItemReachListener(1, layoutManager) {
            viewModel.loadNewData()
        }

        viewModel.loadNewData()

        viewModel.imagesListLiveData.observe(viewLifecycleOwner) {
            imagesListAdapter.updateList(it)
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            imagesListAdapter.setLoading(it)
        }
        viewModel.showErrorLiveData.observe(viewLifecycleOwner) {
            imagesListAdapter.setError(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun navigateToDetailsFragment(imageId: Long) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(id, DetailsFragment.newInstance(imageId))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
