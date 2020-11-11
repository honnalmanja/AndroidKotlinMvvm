package com.honnalmanja.androidkotlinmvvm.data.repository.task.source

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import okhttp3.internal.concurrent.Task

interface TaskLocalDataSource {

    suspend fun getAuthToken(): String?

    suspend fun getATask(id: String?): Task?

    suspend fun getAllTask(): List<Tasks>?

    suspend fun saveATask(task: Tasks)

    suspend fun saveAllTasks(taskList: List<Tasks>)

    suspend fun deleteATask(id: String?)

    suspend fun deleteAllTask()

}

