package com.honnalmanja.androidkotlinmvvm.presentation.view.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentTaskListBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.TaskViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.TaskViewModelFactory
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import javax.inject.Inject

class TaskListFragment : Fragment() {

    private val _TAG = "TaskListFragment"

    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    lateinit var viewModel: TaskViewModel

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
        (activity?.application as Injector).createTaskSubComponent().inject(this)
        viewModel = ViewModelProvider(this, taskViewModelFactory).get(TaskViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getUserToken().observe(viewLifecycleOwner , Observer { token ->
            LogUtils.logD(_TAG, "inside getUserToken")
            if (token != null) {
                LogUtils.logD(_TAG, token)
            } else {
                LogUtils.logD(_TAG, "Token null")
            }
            if (token == null){
                findNavController().navigate(R.id.LoginFragment)
            }
        })

        binding.taskAddFab.setOnClickListener {
            findNavController().navigate(R.id.action_TaskListFragment_to_AddTaskFragment)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if(!isLogin){
//            Log.e("TaskListFragment","Inside false")
//            findNavController().navigate(R.id.LoginFragment)
//        }
//    }
}