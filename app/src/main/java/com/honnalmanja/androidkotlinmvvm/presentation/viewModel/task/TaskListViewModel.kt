package com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil

class TaskListViewModel(private val taskUseCase: TaskUseCase): ViewModel() {

    private val _TAG = "TaskViewModel"

    fun getUserToken() = liveData {

        val response = taskUseCase.getAuthToken()
        emit(response)

    }

    fun getTaskList() = liveData {
        val response = taskUseCase.getAllTask()
        emit(response)
    }

    fun updateTaskList(tasks: Tasks) = liveData {
        val response = taskUseCase.updateTask(tasks)
        emit(response)
    }

}