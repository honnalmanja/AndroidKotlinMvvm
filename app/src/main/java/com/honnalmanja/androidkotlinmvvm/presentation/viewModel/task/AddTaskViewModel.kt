package com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase

class AddTaskViewModel(private val taskUseCase: TaskUseCase): ViewModel() {

    private val TAG = "AddTaskViewModel"

    val task = MutableLiveData<String>()

    fun addNewTaskTask(task: String) = liveData{
        val response = taskUseCase.addATask(AddTaskRequest(task, false))
        emit(response)
    }

}