package com.honnalmanja.androidkotlinmvvm.presentation.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentSignUpBinding
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity

class SignUpFragment : Fragment() {

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as TaskActivity).toggleTaskBar()
        (activity as TaskActivity).pageTitle(getString(R.string.sign_up_title))

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}