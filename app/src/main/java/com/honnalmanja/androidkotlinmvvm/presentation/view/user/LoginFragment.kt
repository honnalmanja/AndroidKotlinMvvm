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
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentLoginBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.LoginViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.LoginViewModelFactory
import javax.inject.Inject


class LoginFragment : Fragment() {

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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as TaskActivity).hideTaskBar()

        binding.loginLoginBtn
            .setOnClickListener {
                postLogin()
            }
        binding.loginSignUpBtn
            .setOnClickListener {
                findNavController().navigate(R.id.action_LoginFragment_to_SignUpFragment)
            }
    }

    private fun postLogin(){

        val email: String = binding.loginEmailEt.text.toString()
        val password: String = binding.loginEmailEt.text.toString()

        viewModel.loginUser(email, password).observe( viewLifecycleOwner, Observer { response ->
            if(response.statusCode == 200){
                findNavController().navigate(R.id.action_LoginFragment_to_TaskListFragment)
            } else {
                Snackbar.make(binding.loginHolderLLc, "${response.message}", Snackbar.LENGTH_LONG).show()
            }
        })


    }
}