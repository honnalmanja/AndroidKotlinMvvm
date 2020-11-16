package com.honnalmanja.androidkotlinmvvm.presentation.view.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentTaskAddBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.AddTaskViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.AddTaskViewModelFactory
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import javax.inject.Inject


class AddTaskFragment : Fragment() {

    private val TAG = "AddTaskFragment"

    @Inject
    lateinit var viewModelFactory: AddTaskViewModelFactory

    lateinit var viewModel: AddTaskViewModel

    lateinit var binding: FragmentTaskAddBinding
    companion object {
        @JvmStatic
        fun newInstance() =
            AddTaskFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity?.application as Injector).createTaskSubComponent().inject(this)
        viewModel  = ViewModelProvider(this, viewModelFactory).get(AddTaskViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_add, container, false)

        binding.addTask = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTaskBar()

        binding.addTaskBtn.setOnClickListener {
            LogUtils.logD(TAG, "Button Clicked")
            addTaskData()
        }

        viewModel.task.observe(viewLifecycleOwner, Observer {
            if(binding.addTaskTil.isErrorEnabled){
                CommonUtil.disableTextError(binding.addTaskTil)
            }
        })


    }

    private fun addTaskData() {

        val task = binding.addTaskEt.text.toString()
        if (task.trim().isBlank() || task.trim().isEmpty()){
            CommonUtil.showInputError("Enter Task Description", binding.addTaskTil)
        } else {
            CommonUtil.disableTextError(binding.addTaskTil)
            viewModel.addNewTaskTask(task).observe(viewLifecycleOwner, Observer {
                findNavController().navigate(R.id.action_AddTaskFragment_to_TaskListFragment)
            })
        }

    }

    private fun initTaskBar() {
        (activity as TaskActivity).toggleTaskBar()
        (activity as TaskActivity).enableBackPress()
        (activity as TaskActivity).pageTitle(getString(R.string.add_task_title))
    }
}