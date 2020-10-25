package com.honnalmanja.androidkotlinmvvm.view.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {

    var isLogin: Boolean = false
    lateinit var binding: FragmentTaskListBinding

    companion object {
        @JvmStatic
        fun newInstance() =
            TaskListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.taskAddFab.setOnClickListener {
            findNavController().navigate(R.id.action_TaskListFragment_to_AddTaskFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        if(!isLogin){
            Log.e("TaskListFragment","Inside false")
            findNavController().navigate(R.id.LoginFragment)
        }
    }
}