package com.honnalmanja.androidkotlinmvvm.data.repository.task.source

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import retrofit2.Response

interface TaskRemoteDataSource {

    suspend fun getAllTasks(token: String?): Response<List<Tasks>>

    suspend fun addTask(token: String?,addTaskRequest: AddTaskRequest): Response<Tasks>

    suspend fun updateTask(token: String?, id: String?, updateTaskRequest: UpdateTaskRequest): Response<Tasks>

    suspend fun deleteTask(token: String?, id: String?): Response<Tasks>

    suspend fun getATask(token: String?, id: String?): Response<Tasks>

}