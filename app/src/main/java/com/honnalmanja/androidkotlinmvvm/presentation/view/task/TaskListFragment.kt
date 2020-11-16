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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentTaskListBinding
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.TaskListViewModel
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.TaskListViewModelFactory
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import javax.inject.Inject

class TaskListFragment : Fragment(), TaskListener {

    private val _TAG = "TaskListFragment"

    @Inject
    lateinit var taskListViewModelFactory: TaskListViewModelFactory

    lateinit var listViewModel: TaskListViewModel

    lateinit var binding: FragmentTaskListBinding

    lateinit var adapter: TaskAdapter

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
        listViewModel = ViewModelProvider(this, taskListViewModelFactory).get(TaskListViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTaskBar()

        initRecyclerView()

        listViewModel.getUserToken().observe(viewLifecycleOwner , Observer { token ->
            LogUtils.logD(_TAG, "inside getUserToken")
            if (token == null){
                findNavController().navigate(R.id.LoginFragment)
            } else {
                callTaskList()
            }
        })

        binding.addTaskBtn.setOnClickListener {
            findNavController().navigate(R.id.action_TaskListFragment_to_AddTaskFragment)
        }
    }

    private fun initRecyclerView() {
        binding.taskListRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = TaskAdapter(this)
        binding.taskListRv.adapter = adapter
    }

    private fun callTaskList() {
        listViewModel.getTaskList().observe(viewLifecycleOwner, Observer { taskLiveData ->
            LogUtils.logD(_TAG, "${taskLiveData.statusCode}")
            if(taskLiveData.statusCode == 200 && !taskLiveData.taskList.isNullOrEmpty()){
                adapter.setAllTasks(taskLiveData.taskList!!)
                binding.taskListRv.adapter = adapter
                adapter.notifyDataSetChanged()
            } else {
                showErrorMessage(if(taskLiveData == null){
                    "Something went wrong"
                }else{
                    taskLiveData.message!!
                })
            }
        })
    }

    private fun initTaskBar() {
        (activity as TaskActivity).toggleTaskBar()
        (activity as TaskActivity).enableBackPress(true)
        (activity as TaskActivity).pageTitle(getString(R.string.task_list_title))
    }

    private fun showErrorMessage(errorMessage: String){
        val snackBar = Snackbar.make(binding.listHolderLlc, errorMessage, Snackbar.LENGTH_LONG)
        snackBar.show()
    }

    override fun onTaskSelected(position: Int, tasks: Tasks) {
        LogUtils.logI("onTaskSelected", "$tasks")
        updateTask(position, tasks)
    }

    private fun updateTask(position: Int, tasks: Tasks) {

        if(tasks.taskID.isNotBlank()) {
            listViewModel.updateTaskList(tasks)
                .observe(viewLifecycleOwner, Observer { taskLiveData ->
                    LogUtils.logD(_TAG, "${taskLiveData.statusCode}")
                    if (taskLiveData.statusCode == 202 && taskLiveData.task != null) {
                        adapter.setATasks(position, taskLiveData.task!!)
                        adapter.notifyItemChanged(position)
                    } else {
                        showErrorMessage(
                            if (taskLiveData == null) {
                                "Something went wrong"
                            } else {
                                taskLiveData.message!!
                            }
                        )
                    }
                })
        }

    }
}