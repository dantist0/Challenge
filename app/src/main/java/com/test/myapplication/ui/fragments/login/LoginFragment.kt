package com.test.myapplication.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.test.myapplication.R
import com.test.myapplication.base.ui.BaseFragment
import com.test.myapplication.databinding.FragmentLoginBinding
import com.test.myapplication.ui.fragments.home.HomeFragment
import com.test.myapplication.ui.fragments.register.RegisterFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class LoginFragment : BaseFragment() {
    private var binding: FragmentLoginBinding? = null
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = checkNotNull(binding)
        binding.loginButton.setOnClickListener {
            viewModel.login(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        binding.registerButton.setOnClickListener {
            navigateToToRegisterScreen()
        }

        viewModel.loginStatusLiveData.observe(viewLifecycleOwner) {
            binding.emailLayout.error = it.emailErrorMessage
            binding.passwordLayout.error = it.passwordErrorMessage
            if(it.isSuccess){
                navigateToHomeScreen()
            }
        }

        viewModel.loginInProgressLiveData.observe(viewLifecycleOwner) {
            binding.loginButton.isInvisible = it
            binding.progressBar.isVisible = it
        }

    }

    private fun navigateToHomeScreen() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(id, HomeFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun navigateToToRegisterScreen(){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(id, RegisterFragment.newInstance())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        fun newInstance() = LoginFragment()
    }
}