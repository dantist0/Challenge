package com.test.myapplication.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseFragment
import com.test.myapplication.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class RegisterFragment : BaseFragment() {
    private var binding: FragmentRegisterBinding? = null
    private val viewModel by viewModel<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = checkNotNull(binding)

        binding.registerButton.setOnClickListener {
            viewModel.register(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString(),
                age = binding.ageEditText.text.toString().toIntOrNull() ?: 0
            )
        }

        viewModel.registerInProgressLiveData.observe(viewLifecycleOwner) {
            binding.registerButton.isInvisible = it
            binding.progressBar.isVisible = it
        }

        viewModel.registerStatusLiveData.observe(viewLifecycleOwner) {
            binding.emailLayout.error = it.emailErrorMessage
            binding.passwordLayout.error = it.passwordErrorMessage
            binding.ageLayout.error = it.ageErrorMessage
            if (it.isSuccess) {
                Toast.makeText(context, getString(R.string.account_has_been_created), Toast.LENGTH_SHORT).show()
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }


    companion object {
        fun newInstance() = RegisterFragment()
    }
}