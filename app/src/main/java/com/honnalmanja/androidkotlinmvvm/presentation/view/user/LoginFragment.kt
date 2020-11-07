package com.honnalmanja.androidkotlinmvvm.presentation.view.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentLoginBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.LoginViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.LoginViewModelFactory
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import javax.inject.Inject


class LoginFragment : Fragment() {

    private val _TAG = "LoginFragment"

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var binding:FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as Injector).createUserSubComponent().inject(this)
        viewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.login = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as TaskActivity).toggleTaskBar(true)

        binding.loginSignUpBtn
            .setOnClickListener {
                findNavController().navigate(R.id.action_LoginFragment_to_SignUpFragment)
            }
        binding.loginForgotBtn.setOnClickListener {
            showErrorMessage("Functionality not yet implemented")
        }

        viewModel.email.observe(viewLifecycleOwner, Observer {
            if(binding.loginEmailTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.loginEmailTil)
            }
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {
            if(binding.loginPasswordTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.loginPasswordTil)
            }
        })

        viewModel.canLogin.observe(viewLifecycleOwner, Observer {
            LogUtils.logD(_TAG, "canLogin: $it")
            if (it){
                postLogin()
            }
        })

        viewModel.emailError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.login_enter_email_error),
                        binding.loginEmailTil
                    )
                }
                2 -> {
                    CommonUtil.showInputError(
                        getString(R.string.login_email_valid_error),
                        binding.loginEmailTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.loginEmailTil)
                }
            }
        })

        viewModel.passwordError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.login_enter_password_error),
                        binding.loginPasswordTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.loginPasswordTil)
                }
            }
        })
    }

    private fun postLogin(){

        //LogUtils.logD(_TAG, "postLogin: Email: ${viewModel.email.value}")
        //LogUtils.logD(_TAG, "postLogin: Password: ${viewModel.password.value}")

        val email: String? = viewModel.email.value
        val password: String? = viewModel.password.value

        viewModel.loginUser(email, password).observe(viewLifecycleOwner, Observer { response ->
            LogUtils.logD(_TAG, "Login ResponseCode: ${response.statusCode}")
            when (response.statusCode) {
                200 -> {
                    findNavController().navigate(R.id.action_LoginFragment_to_TaskListFragment)
                }
                500 -> {
                    showErrorMessage(
                        getString(R.string.server_not_reachable_text)
                    )
                }
                else -> {
                    showErrorMessage("${response.message}")
                }
            }
        })

    }

    private fun showErrorMessage(message: String){
        Snackbar.make(binding.loginHolderLLc, message, Snackbar.LENGTH_LONG).show()
    }
}