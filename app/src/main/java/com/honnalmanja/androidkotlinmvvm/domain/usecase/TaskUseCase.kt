package com.honnalmanja.androidkotlinmvvm.domain.usecase

import com.honnalmanja.androidkotlinmvvm.data.model.app.TaskLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.domain.repository.TaskRepository

class TaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun getAuthToken(): String? {
        return taskRepository.getAuthToken()
    }

    suspend fun getAllTask(): TaskLiveData {
        return taskRepository.getAllTasks()
    }

    suspend fun getATask(id: String?): TaskLiveData {
        return taskRepository.getATasks(id)
    }

    suspend fun addATask(addTaskRequest: AddTaskRequest): TaskLiveData {
        return taskRepository.addATasks(addTaskRequest)
    }

    suspend fun updateTask(tasks: Tasks): TaskLiveData {
        return taskRepository.updateATasks(tasks)
    }

    suspend fun deleteATask(id: String?): TaskLiveData {
        return taskRepository.deleteATask(id)
    }

    suspend fun deleteAllTask(): TaskLiveData{
        return taskRepository.deleteAllTask()
    }

}