package com.honnalmanja.androidkotlinmvvm.data.repository.task.impl

import com.honnalmanja.androidkotlinmvvm.data.api.TMServices
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.TaskResponse
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import retrofit2.Response

class TaskRemoteDataSourceImpl(
    private val service: TMServices
): TaskRemoteDataSource {
    override suspend fun getAllTasks(token: String?): Response<TaskResponse> {
        LogUtils.logD("TaskRemoteDataSourceImpl", "getAllTasks")
        return service.getAllTasks(token)
    }

    override suspend fun addTask(token: String?, addTaskRequest: AddTaskRequest): Response<Tasks> {
        return service.addTask(token, addTaskRequest)
    }

    override suspend fun updateTask(token: String?, id: String, updateTaskRequest: UpdateTaskRequest): Response<Tasks> {
        return service.updateTask(token, id, updateTaskRequest)
    }

    override suspend fun deleteTask(token: String?, id: String?): Response<Tasks> {
        return service.deleteTask(token, id)
    }

    override suspend fun getATask(token: String?, id: String?): Response<Tasks> {
        return service.getATask(token, id)
    }

}