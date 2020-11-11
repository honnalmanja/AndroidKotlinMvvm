package com.honnalmanja.androidkotlinmvvm.presentation.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentSignUpBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.SignUpViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.SignUpViewModelFactory
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private val _TAG = "SignUpFragment"

    @Inject
    lateinit var signUpViewModelFactory: SignUpViewModelFactory

    lateinit var viewModel: SignUpViewModel

    private lateinit var binding:FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as Injector).createUserSubComponent().inject(this)

        viewModel = ViewModelProvider(this, signUpViewModelFactory).get(SignUpViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.signUp = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskBarInit()

        viewModel.canCreateUser.observe(viewLifecycleOwner, Observer {
            if(it){
                postUser()
            }
        })

        // observing text field entered
        viewModel.email.observe(viewLifecycleOwner, Observer {
            if(binding.signUpEmailTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.signUpEmailTil)
            }
        })

        viewModel.name.observe(viewLifecycleOwner, Observer {
            if(binding.signUpNameTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.signUpNameTil)
            }
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {
            if(binding.signUpPasswordTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.signUpPasswordTil)
            }
        })

        viewModel.confirmPassword.observe(viewLifecycleOwner, Observer {
            if(binding.signUpConfirmPasswordTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.signUpConfirmPasswordTil)
            }
        })


        // Observing error status
        viewModel.emailError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.enter_email_error),
                        binding.signUpEmailTil
                    )
                }
                2 -> {
                    CommonUtil.showInputError(
                        getString(R.string.login_email_valid_error),
                        binding.signUpEmailTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.signUpEmailTil)
                }
            }
        })

        viewModel.nameError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.sign_up_enter_name_error),
                        binding.signUpNameTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.signUpNameTil)
                }
            }
        })

        viewModel.passwordError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.enter_password_error),
                        binding.signUpPasswordTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.signUpPasswordTil)
                }
            }
        })

        viewModel.cPasswordError.observe(viewLifecycleOwner, Observer {
            when(it) {
                CommonUtil.nullBlankErrorCode -> {
                    CommonUtil.showInputError(
                        getString(R.string.enter_password_error),
                        binding.signUpConfirmPasswordTil
                    )
                }
                2 -> {
                    CommonUtil.showInputError(
                        getString(R.string.sign_up_password_not_match),
                        binding.signUpConfirmPasswordTil
                    )
                    CommonUtil.showInputError(
                        getString(R.string.sign_up_password_not_match),
                        binding.signUpPasswordTil
                    )
                }
                CommonUtil.successCode -> {
                    CommonUtil.disableTextError(binding.signUpConfirmPasswordTil)
                    CommonUtil.disableTextError(binding.signUpPasswordTil)
                }
            }
        })

    }

    private fun taskBarInit(){
        (activity as TaskActivity).toggleTaskBar()
        (activity as TaskActivity).pageTitle(getString(R.string.sign_up_title))
        (activity as TaskActivity).enableBackPress(false)
    }

    private fun postUser(){

        val email: String? = viewModel.email.value
        val name: String? = viewModel.name.value
        val password: String? = viewModel.password.value

        viewModel.postUser(email,name, password).observe(viewLifecycleOwner, Observer { response ->
            LogUtils.logD(_TAG, "Create user ResponseCode: ${response.statusCode}")
            when (response.statusCode) {
                200 -> {
                    findNavController().navigate(R.id.action_SignUpFragment_to_LoginFragment)
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

    companion object {

        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun showErrorMessage(message: String){
        Snackbar.make(binding.signUpHolderLlc, message, Snackbar.LENGTH_LONG).show()
    }
}