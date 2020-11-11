package com.honnalmanja.androidkotlinmvvm.data.repository.task

import com.honnalmanja.androidkotlinmvvm.data.model.app.TaskLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskCacheDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskRemoteDataSource: TaskRemoteDataSource,
    private val taskLocalDataSource: TaskLocalDataSource,
    private val taskCacheDataSource: TaskCacheDataSource,
): TaskRepository {
    override suspend fun getAuthToken(): String? {
        return taskLocalDataSource.getAuthToken()
    }

    override suspend fun getAllTasks(): TaskLiveData {
        val response = taskRemoteDataSource.getAllTasks(taskLocalDataSource.getAuthToken())
        return TaskLiveData(
            response.code(), response.message(), null, response.body()
        )
    }

    override suspend fun getATasks(id: String?): TaskLiveData {
       val response = taskRemoteDataSource.getATask(taskLocalDataSource.getAuthToken(), id)
        return TaskLiveData(
            response.code(), response.message(), response.body(),null
        )
    }

    override suspend fun addATasks(addTaskRequest: AddTaskRequest): TaskLiveData {
        TODO("Not yet implemented")
    }

    override suspend fun updateATasks(
        id: String?,
        updateTaskRequest: UpdateTaskRequest
    ): TaskLiveData {
        TODO("Not yet implemented")
    }

    override suspend fun deleteATask(id: String?) : TaskLiveData {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTask() : TaskLiveData {
        TODO("Not yet implemented")
    }

}