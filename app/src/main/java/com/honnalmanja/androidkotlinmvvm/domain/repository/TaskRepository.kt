package com.honnalmanja.androidkotlinmvvm.domain.repository

import com.honnalmanja.androidkotlinmvvm.data.model.app.TaskLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest

interface TaskRepository {

    suspend fun getAuthToken(): String?

    suspend fun getAllTasks(): TaskLiveData

    suspend fun getATasks(id: String?): TaskLiveData

    suspend fun addATasks(addTaskRequest: AddTaskRequest): TaskLiveData

    suspend fun updateATasks(id: String?, updateTaskRequest: UpdateTaskRequest): TaskLiveData

    suspend fun deleteATask(id: String?): TaskLiveData

    suspend fun deleteAllTask(): TaskLiveData

}